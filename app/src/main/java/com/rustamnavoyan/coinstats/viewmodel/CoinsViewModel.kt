package com.rustamnavoyan.coinstats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.rustamnavoyan.coinstats.data.GetCoinsWorker
import com.rustamnavoyan.coinstats.data.model.CoinRealmDataModel
import com.rustamnavoyan.coinstats.domain.UseCaseExecutor
import com.rustamnavoyan.coinstats.domain.usecase.GetCoinsUseCase
import com.rustamnavoyan.coinstats.viewmodel.mapper.CoinRealmResultsToDataModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import io.realm.kotlin.where
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val COINS_SORT_FIELD = "id"
private const val GET_COINS_PERIODIC_WORK_UNIQUE_ID = "get_coins_periodic_work_unique_id"
private const val GET_COINS_PERIODIC_MINUTES = 15L

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val useCaseExecutor: UseCaseExecutor,
    private val realm: Realm,
    private val coinRealmResultsToDataModelMapper: CoinRealmResultsToDataModelMapper,
    private val workManager: WorkManager
) : ViewModel() {

    private var _coinsViewState = MutableLiveData<CoinsViewState>().apply {
        value = CoinsViewState(isLoading = true, coins = emptyList())
    }
    val coinsViewState: LiveData<CoinsViewState>
        get() = _coinsViewState

    init {
        registerRealmChangeListener()
        updateCoinsPeriodically()
    }

    fun onViewCreated() {
        useCaseExecutor.execute(useCase = getCoinsUseCase)
    }

    private fun updateCoinsPeriodically() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        workManager.enqueueUniquePeriodicWork(
            GET_COINS_PERIODIC_WORK_UNIQUE_ID,
            ExistingPeriodicWorkPolicy.REPLACE,
            PeriodicWorkRequestBuilder<GetCoinsWorker>(
                GET_COINS_PERIODIC_MINUTES,
                TimeUnit.MINUTES
            ).setConstraints(constraints).build()
        )
    }

    private fun registerRealmChangeListener() {
        realm.where<CoinRealmDataModel>()
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
