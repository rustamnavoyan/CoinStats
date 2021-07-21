package com.rustamnavoyan.coinstats.viewmodel.mapper

import com.rustamnavoyan.coinstats.data.model.CoinDataModel
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel
import io.realm.RealmResults

class CoinRealmResultsToDataModelMapper {
    fun toData(realmResults: RealmResults<CoinRealmDataModel>) = realmResults.map { coin ->
        CoinDataModel(
            id = coin.id,
            iconUrl = coin.iconUrl,
            name = coin.name,
            changeIn24Hours = coin.changeIn24Hours,
            price = coin.price
        )
    }
}
