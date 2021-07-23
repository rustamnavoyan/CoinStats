package com.rustamnavoyan.coinstats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel
import com.rustamnavoyan.coinstats.domain.UseCaseExecutor
import com.rustamnavoyan.coinstats.domain.usecase.GetCoinsUseCase
import com.rustamnavoyan.coinstats.domain.usecase.ToggleFavoriteUseCase
import com.rustamnavoyan.coinstats.viewmodel.mapper.CoinRealmResultsToDataModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import io.realm.kotlin.where
import javax.inject.Inject

private const val COINS_SORT_FIELD = "id"

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val useCaseExecutor: UseCaseExecutor,
    private val realm: Realm,
    private val coinRealmResultsToDataModelMapper: CoinRealmResultsToDataModelMapper,
) : ViewModel() {

    private var _coinsViewState = MutableLiveData<CoinsViewState>().apply {
        value = CoinsViewState(isLoading = true, coins = emptyList())
    }
    val coinsViewState: LiveData<CoinsViewState>
        get() = _coinsViewState

    init {
        registerRealmChangeListener()
    }

    fun toggleFavorite(coinName: String) {
        useCaseExecutor.execute(
            useCase = toggleFavoriteUseCase,
            value = coinName
        )
    }

    private fun registerRealmChangeListener() {
        realm.where<CoinRealmDataModel>()
            .equalTo("isFavorite", true)
            .findAllAsync().addChangeListener { coins ->
                coins.sort(COINS_SORT_FIELD)
                _coinsViewState.apply {
                    value = value?.copy(
                        isLoading = false,
                        coins = coinRealmResultsToDataModelMapper.toData(coins)
                    )
                }
            }
    }
}
