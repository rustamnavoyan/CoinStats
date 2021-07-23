package com.rustamnavoyan.coinstats.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rustamnavoyan.coinstats.ui.base.CoinsFragmentBase
import com.rustamnavoyan.coinstats.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : CoinsFragmentBase() {
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.coinsViewState.observe(
            viewLifecycleOwner,
            { viewState -> renderViewState(viewState) }
        )

        coinAdapter.itemClickListener = { coin ->
            viewModel.toggleFavorite(coin.name)
        }
    }
}
