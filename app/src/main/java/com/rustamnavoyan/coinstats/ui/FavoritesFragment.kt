package com.rustamnavoyan.coinstats.ui

import com.rustamnavoyan.coinstats.ui.base.CoinsFragmentBase
import com.rustamnavoyan.coinstats.ui.model.CoinItem

class FavoritesFragment : CoinsFragmentBase() {

    override fun initialItems() =
        listOf(
            // TODO Dummy data. Should be replaced with the real data
            CoinItem(
                name = "DogeCoin",
                changeIn24Hours = 0.33,
                positiveChange = false,
                price = 31.564,
                iconUrl = "https://static.coinstats.app/coins/DogecoinIZai5.png"
            ),
            CoinItem(
                name = "Ethereum",
                changeIn24Hours = 2.07,
                positiveChange = false,
                price = 1.864,
                iconUrl = "https://static.coinstats.app/coins/EthereumOCjgD.png"
            ),
            CoinItem(
                name = "Tether",
                changeIn24Hours = 4.82,
                positiveChange = true,
                price = 134.0,
                iconUrl = "https://static.coinstats.app/coins/TetherfopnG.png"
            )
        )
}
