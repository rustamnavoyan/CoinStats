package com.rustamnavoyan.coinstats.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rustamnavoyan.coinstats.ui.CoinsFragment
import com.rustamnavoyan.coinstats.ui.FavoritesFragment

const val COINS_FRAGMENT_POSITION = 0
const val FAVORITES_FRAGMENT_POSITION = 1 // Unused

class CoinStatsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        COINS_FRAGMENT_POSITION -> CoinsFragment()
        else -> FavoritesFragment()
    }
}
