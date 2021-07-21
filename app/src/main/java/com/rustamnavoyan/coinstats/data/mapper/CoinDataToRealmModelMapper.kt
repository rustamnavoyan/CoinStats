package com.rustamnavoyan.coinstats.data.mapper

import com.rustamnavoyan.coinstats.data.model.CoinDataModel
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel

class CoinDataToRealmModelMapper {
    fun toRealm(coinDataModel: CoinDataModel) = CoinRealmDataModel(
        id = coinDataModel.id,
        iconUrl = coinDataModel.iconUrl,
        name = coinDataModel.name,
        changeIn24Hours = coinDataModel.changeIn24Hours,
        price = coinDataModel.price
    )
}
