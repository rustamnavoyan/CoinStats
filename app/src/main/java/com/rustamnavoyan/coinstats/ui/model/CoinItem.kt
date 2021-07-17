package com.rustamnavoyan.coinstats.ui.model

data class CoinItem(
    val name: String,
    val changeIn24Hours: Double,
    val positiveChange: Boolean,
    val price: Double,
    val iconUrl: String
)
