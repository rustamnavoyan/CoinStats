package com.rustamnavoyan.coinstats.domain.usecase

import com.rustamnavoyan.coinstats.data.CoinRepository
import com.rustamnavoyan.coinstats.domain.BackgroundExecuteUseCase
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) : BackgroundExecuteUseCase<String, Unit>() {
    override suspend fun executeInBackground(
        request: String,
        callback: (Unit) -> Unit
    ) {
        coinRepository.toggleFavorite(request)
    }
}
