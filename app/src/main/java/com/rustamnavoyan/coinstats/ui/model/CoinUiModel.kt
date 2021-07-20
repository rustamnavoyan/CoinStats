package com.rustamnavoyan.coinstats.ui.model

data class CoinUiModel(
    val name: String,
    val changeIn24Hours: Double,
    val positiveChange: Boolean,
    val price: Double,
    val iconUrl: String
)
