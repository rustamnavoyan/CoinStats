package com.rustamnavoyan.coinstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rustamnavoyan.coinstats.ui.adapter.COINS_FRAGMENT_POSITION
import com.rustamnavoyan.coinstats.ui.adapter.CoinStatsPagerAdapter

class MainFragment : Fragment() {

    private val viewPager: ViewPager2 get() = requireView().findViewById(R.id.view_pager)
    private val tabLayout: TabLayout get() = requireView().findViewById(R.id.tab_layout)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = CoinStatsPagerAdapter(fragment = this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(
                when (position) {
                    COINS_FRAGMENT_POSITION -> R.string.coins_fragment_tab_text
                    else -> R.string.favorites_fragment_tab_text
                }
            )
        }.attach()
    }
}
