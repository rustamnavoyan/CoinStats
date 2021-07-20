package com.rustamnavoyan.coinstats.domain

import com.rustamnavoyan.coinstats.domain.exception.DomainException
import com.rustamnavoyan.coinstats.domain.exception.UnknownDomainException

interface BaseUseCase<REQUEST, RESULT> {
    suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}
