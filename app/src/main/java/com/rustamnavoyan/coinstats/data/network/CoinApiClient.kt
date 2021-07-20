package com.rustamnavoyan.coinstats.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinApiClient {
    private var coinService: CoinService

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        coinService = retrofit.create(CoinService::class.java)
    }

    fun getCoinService() = coinService

    companion object {
        // TODO Keeping Url here is not a good idea
        private const val BASE_URL = "https://api.coin-stats.com/"
    }
}
