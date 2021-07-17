package com.rustamnavoyan.coinstats.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamnavoyan.coinstats.R
import com.rustamnavoyan.coinstats.ui.adapter.CoinAdapter
import com.rustamnavoyan.coinstats.ui.model.CoinItem

abstract class CoinsFragmentBase : Fragment() {
    private val coinsRecyclerView: RecyclerView get() = requireView().findViewById(R.id.coins_recycler_view)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coinAdapter = CoinAdapter().apply {
            items = initialItems()
        }
        coinsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coinAdapter
        }
    }

    abstract fun initialItems(): List<CoinItem>
}
