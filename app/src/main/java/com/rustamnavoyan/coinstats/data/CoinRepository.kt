package com.rustamnavoyan.coinstats.data

import com.rustamnavoyan.coinstats.data.mapper.CoinDataToRealmModelMapper
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel
import com.rustamnavoyan.coinstats.data.network.CoinService
import dagger.Lazy
import io.realm.Realm
import io.realm.kotlin.where
import javax.inject.Inject

const val DB_FIELD_IS_FAVORITE = "isFavorite"
const val DB_FIELD_NAME = "name"

class CoinRepository @Inject constructor(
    private val coinService: CoinService,
    private val coinDataToRealmModelMapper: CoinDataToRealmModelMapper,
) {

    @Inject
    lateinit var realm: Lazy<Realm>

    fun getCoins() {
        coinService.getCoins().execute().body()?.coins?.let { coins ->
            realm.get().use { realm ->
                val coinsRealm = coins.map(coinDataToRealmModelMapper::toRealm)
                realm.executeTransaction { transactionRealm ->
                    val favorites = transactionRealm.where<CoinRealmDataModel>()
                        .equalTo(DB_FIELD_IS_FAVORITE, true)
                        .findAll()
                    coinsRealm.filter { coin ->
                        favorites.any { it.id == coin.id }
                    }.forEach {
                        it.isFavorite = true
                    }
                    transactionRealm.copyToRealmOrUpdate(coinsRealm)
                }
            }
        }
    }

    fun toggleFavorite(coinName: String) {
        realm.get().apply { // TODO Crashes after one invocation if closed
            executeTransactionAsync {
                val coin: CoinRealmDataModel? =
                    it.where(CoinRealmDataModel::class.java).equalTo(DB_FIELD_NAME, coinName)
                        .findFirst()
                coin?.apply {
                    isFavorite = !isFavorite
                }
            }
        }
    }
}
