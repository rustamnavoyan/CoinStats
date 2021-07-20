package com.rustamnavoyan.coinstats.viewmodel

import com.rustamnavoyan.coinstats.data.model.CoinDataModel

data class CoinsViewState(
    val isLoading: Boolean,
    val coins: List<CoinDataModel>
)
