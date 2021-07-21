package com.rustamnavoyan.coinstats.data.model

import com.google.gson.annotations.SerializedName

data class CoinDataModel(
    @SerializedName("r") val id: Int,
    @SerializedName("ic") val iconUrl: String,
    @SerializedName("n") val name: String,
    @SerializedName("p24") val changeIn24Hours: Double,
    @SerializedName("pu") val price: Double,
)
