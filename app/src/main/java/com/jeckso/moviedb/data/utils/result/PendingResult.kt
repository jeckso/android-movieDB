package com.jeckso.moviedb.data.utils.result

sealed class PendingResult<T> {
    abstract val data: T
}

data class Success<T>(
    override val data: T
) : PendingResult<T>()

data class ErrorResult(
    val code: Int,
    val message: String
) : PendingResult<Nothing>() {

    override val data: Nothing = throw IllegalArgumentException()
}