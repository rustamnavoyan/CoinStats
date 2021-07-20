package com.rustamnavoyan.coinstats.domain

import com.rustamnavoyan.coinstats.domain.exception.DomainException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

open class UseCaseExecutor @Inject constructor(private var coroutineScope: CoroutineScope) {

    open fun <OUT_TYPE> execute(
        useCase: BaseUseCase<Unit, OUT_TYPE>,
        callback: (OUT_TYPE) -> Unit = {},
        onError: (DomainException) -> Unit = {}
    ) = execute(useCase, Unit, callback, onError)

    open fun <IN_TYPE, OUT_TYPE> execute(
        useCase: BaseUseCase<IN_TYPE, OUT_TYPE>,
        value: IN_TYPE,
        callback: (OUT_TYPE) -> Unit = {},
        onError: (DomainException) -> Unit = {}
    ): RunningExecution {
        val job = launchUseCaseExecution(useCase, value, callback, onError)
        return RunningUseCaseExecution(job)
    }

    private fun <IN_TYPE, OUT_TYPE> launchUseCaseExecution(
        useCase: BaseUseCase<IN_TYPE, OUT_TYPE>,
        value: IN_TYPE,
        callback: (OUT_TYPE) -> Unit,
        onError: (DomainException) -> Unit
    ) = coroutineScope.launch {
        try {
            useCase.execute(value, callback)
        } catch (cancellationException: CancellationException) {
            // Handle cancellation error
        } catch (throwable: Throwable) {
            onError(
                if (throwable is DomainException) {
                    throwable
                } else {
                    useCase.onError(throwable)
                }
            )
        }
    }

    interface RunningExecution {
        fun isRunning(): Boolean
    }

    class RunningUseCaseExecution(private val job: Job) : RunningExecution {
        override fun isRunning() = job.isActive
    }
}
