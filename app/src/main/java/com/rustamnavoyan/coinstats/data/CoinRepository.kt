package com.rustamnavoyan.coinstats.data

import com.rustamnavoyan.coinstats.data.mapper.CoinDataToRealmModelMapper
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel
import com.rustamnavoyan.coinstats.data.network.CoinService
import dagger.Lazy
import io.realm.Realm
import io.realm.kotlin.delete
import javax.inject.Inject

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
                realm.executeTransaction {
                    it.delete<CoinRealmDataModel>()
                    it.insertOrUpdate(coinsRealm)
                }
            }
        }
    }
}
