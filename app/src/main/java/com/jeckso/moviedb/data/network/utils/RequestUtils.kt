package com.jeckso.moviedb.data.network.utils

import com.jeckso.moviedb.data.utils.result.ErrorResult
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.data.utils.result.Success
import retrofit2.HttpException
import java.net.ConnectException

const val NO_CONNECTION_CODE = -1
const val UNSCPECIFIED_ERROR_CODE = -2


suspend fun <T> wrapRequest(block: suspend () -> T): PendingResult<*> = try {
    Success(block())
} catch (ex: HttpException) {
    ErrorResult(ex.code(), ex.message())
} catch (ex: ConnectException) {
    ErrorResult(NO_CONNECTION_CODE, ex.message ?: "")
} catch (ex: Exception) {
    ErrorResult(UNSCPECIFIED_ERROR_CODE, ex.message ?: "")
}
