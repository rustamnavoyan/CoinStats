package com.rustamnavoyan.coinstats.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CoinRealmDataModel(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var iconUrl: String = "",
    var changeIn24Hours: Double = 0.0,
    var price: Double = 0.0,
    var isFavorite: Boolean = false
) : RealmObject()
