package com.rustamnavoyan.coinstats.data.network

import com.rustamnavoyan.coinstats.data.model.CoinResponseDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CoinService {
    @GET("/v2/coins/?skip=0&limit=100")
    fun getCoins(): Call<CoinResponseDataModel?>

    @GET
    fun getArticle(
        @Url url: String?,
        @Query("api-key") apiKey: String?,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") showFields: String?
    ): Call<CoinResponseDataModel?>
}
