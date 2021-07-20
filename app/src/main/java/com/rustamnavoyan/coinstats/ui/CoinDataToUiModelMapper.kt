package com.rustamnavoyan.coinstats.ui

import com.rustamnavoyan.coinstats.data.model.CoinDataModel
import com.rustamnavoyan.coinstats.ui.model.CoinUiModel
import kotlin.math.absoluteValue

class CoinDataToUiModelMapper {
    fun toUi(coin: CoinDataModel) = CoinUiModel(
        iconUrl = coin.iconUrl,
        name = coin.name,
        changeIn24Hours = coin.changeIn24Hours.absoluteValue,
        positiveChange = coin.changeIn24Hours >= 0.0,
        price = coin.price
    )
}
