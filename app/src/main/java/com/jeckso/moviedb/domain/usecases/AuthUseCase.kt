package com.jeckso.moviedb.domain.usecases

import com.jeckso.moviedb.data.manager.AuthPreferencesManager
import com.jeckso.moviedb.data.models.AuthResponseModel
import com.jeckso.moviedb.data.network.rest.service.AuthService
import com.jeckso.moviedb.data.network.utils.wrapRequest
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.data.utils.result.Success
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authService: AuthService,
    private val authPreferencesManager: AuthPreferencesManager
) {

    var token: String
        set(value) {
            authPreferencesManager.token = value
        }
        get() = authPreferencesManager.token

    suspend fun authorize(email: String, pass: String): PendingResult<*> = wrapRequest {
        authService.authorize(email, pass)
    }.also {
        val token = (it as? Success<AuthResponseModel>)?.data?.token
        this.token = token ?: ""
    }

    suspend fun register(email: String, pass: String): PendingResult<*> = wrapRequest {
        authService.register(email, pass)
    }
}