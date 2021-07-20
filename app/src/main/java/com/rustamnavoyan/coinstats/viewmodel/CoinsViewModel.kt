package com.rustamnavoyan.coinstats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rustamnavoyan.coinstats.domain.UseCaseExecutor
import com.rustamnavoyan.coinstats.domain.usecase.GetCoinsUseCase
import com.rustamnavoyan.coinstats.data.model.CoinDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val useCaseExecutor: UseCaseExecutor
) : ViewModel() {

    private var _coinsViewState = MutableLiveData<CoinsViewState>().apply {
        value = CoinsViewState(isLoading = true, coins = emptyList())
    }
    val coinsViewState: LiveData<CoinsViewState>
        get() = _coinsViewState

    fun onViewCreated() {
        useCaseExecutor.execute(
            useCase = getCoinsUseCase,
            callback = this::handleResult
        )
    }

    private fun handleResult(coins: List<CoinDataModel>) {
        _coinsViewState.apply {
            value = value?.copy(isLoading = false, coins = coins)
        }
    }
}
