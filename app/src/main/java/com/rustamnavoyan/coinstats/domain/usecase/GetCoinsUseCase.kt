package com.rustamnavoyan.coinstats.domain.usecase

import com.rustamnavoyan.coinstats.domain.BackgroundExecuteUseCase
import com.rustamnavoyan.coinstats.data.CoinRepository
import com.rustamnavoyan.coinstats.data.model.CoinDataModel
import com.rustamnavoyan.coinstats.data.model.CoinResponseDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) : BackgroundExecuteUseCase<Unit, List<CoinDataModel>>() {
    override suspend fun executeInBackground(
        request: Unit,
        callback: (List<CoinDataModel>) -> Unit
    ) {
        coinRepository.getCoins().enqueue(object : Callback<CoinResponseDataModel?> {
            override fun onResponse(call: Call<CoinResponseDataModel?>, response: Response<CoinResponseDataModel?>) {
                response.body()?.coins?.let(callback) ?: callback(emptyList())
            }
            override fun onFailure(call: Call<CoinResponseDataModel?>, t: Throwable) {}
        })
    }
}
