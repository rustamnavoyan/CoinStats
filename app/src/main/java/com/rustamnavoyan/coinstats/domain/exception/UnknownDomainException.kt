package com.rustamnavoyan.coinstats.domain.exception

data class UnknownDomainException(override val throwable: Throwable) : DomainException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
