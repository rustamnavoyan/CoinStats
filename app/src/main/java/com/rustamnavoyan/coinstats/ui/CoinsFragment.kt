package com.rustamnavoyan.coinstats.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rustamnavoyan.coinstats.ui.base.CoinsFragmentBase
import com.rustamnavoyan.coinstats.viewmodel.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsFragment : CoinsFragmentBase() {
    private val viewModel: CoinsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()

        coinAdapter.itemClickListener = { coin ->
            viewModel.toggleFavorite(coin.name)
        }

        viewModel.coinsViewState.observe(
            viewLifecycleOwner,
            { viewState -> renderViewState(viewState) }
        )
    }
}
