package com.rustamnavoyan.coinstats.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BackgroundExecuteUseCase<REQUEST, RESULT> : BaseUseCase<REQUEST, RESULT> {
    final override suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit) {
        withContext(Dispatchers.IO) {
            executeInBackground(value) { result ->
                CoroutineScope(Dispatchers.Main).launch {
                    callback(result)
                }
            }
        }
    }

    abstract suspend fun executeInBackground(
        request: REQUEST,
        callback: (RESULT) -> Unit
    )
}
