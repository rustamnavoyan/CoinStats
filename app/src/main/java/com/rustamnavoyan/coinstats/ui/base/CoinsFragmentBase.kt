package com.rustamnavoyan.coinstats.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rustamnavoyan.coinstats.R
import com.rustamnavoyan.coinstats.ui.CoinDataToUiModelMapper
import com.rustamnavoyan.coinstats.ui.adapter.CoinAdapter
import com.rustamnavoyan.coinstats.viewmodel.CoinsViewState
import javax.inject.Inject

abstract class CoinsFragmentBase : Fragment() {
    private val coinsRecyclerView: RecyclerView get() = requireView().findViewById(R.id.coins_recycler_view)
    private val progressBar: View get() = requireView().findViewById(R.id.progress_view)

    @Inject
    lateinit var dataToUiModelMapper: CoinDataToUiModelMapper

    @Inject
    lateinit var coinAdapter: CoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coinAdapter
        }
    }

    fun renderViewState(viewState: CoinsViewState) {
        progressBar.isVisible = viewState.isLoading
        coinAdapter.items = viewState.coins.map(dataToUiModelMapper::toUi)
    }
}
