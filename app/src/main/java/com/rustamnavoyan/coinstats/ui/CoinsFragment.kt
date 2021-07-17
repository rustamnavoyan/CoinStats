package com.rustamnavoyan.coinstats.ui

import com.rustamnavoyan.coinstats.ui.base.CoinsFragmentBase
import com.rustamnavoyan.coinstats.ui.model.CoinItem

class CoinsFragment : CoinsFragmentBase() {
    override fun initialItems() =
        // TODO Dummy data. Should be replaced with the real data
        listOf(
            CoinItem(
                name = "BitCoin",
                changeIn24Hours = 1.33,
                positiveChange = true,
                price = 31564.14,
                iconUrl = "https://static.coinstats.app/coins/Bitcoin6l39t.png"
            ),
            CoinItem(
                name = "Ethereum",
                changeIn24Hours = 2.07,
                positiveChange = false,
                price = 1864.01,
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
