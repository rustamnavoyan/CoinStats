package com.rustamnavoyan.coinstats.data

import com.rustamnavoyan.coinstats.data.network.CoinService
import javax.inject.Inject

class CoinRepository @Inject constructor(private val coinService: CoinService) {
    fun getCoins() = coinService.getCoins()
}
