package com.rustamnavoyan.coinstats.data

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rustamnavoyan.coinstats.domain.UseCaseExecutor
import com.rustamnavoyan.coinstats.domain.usecase.GetCoinsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GetCoinsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getCoinsUseCase: GetCoinsUseCase,
    private var useCaseExecutor: UseCaseExecutor
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        useCaseExecutor.execute(useCase = getCoinsUseCase)
        return Result.success()
    }
}
