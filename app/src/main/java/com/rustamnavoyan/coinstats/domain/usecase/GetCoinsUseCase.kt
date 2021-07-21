package com.rustamnavoyan.coinstats.domain.usecase

import com.rustamnavoyan.coinstats.data.CoinRepository
import com.rustamnavoyan.coinstats.domain.BackgroundExecuteUseCase
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) : BackgroundExecuteUseCase<Unit, Unit>() {
    override suspend fun executeInBackground(
        request: Unit,
        callback: (Unit) -> Unit
    ) {
        coinRepository.getCoins()
    }
}
