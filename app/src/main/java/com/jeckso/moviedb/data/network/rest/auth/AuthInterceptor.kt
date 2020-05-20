package com.jeckso.moviedb.data.network.rest.auth

import com.jeckso.moviedb.BuildConfig
import com.jeckso.moviedb.data.manager.AuthPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authPreferencesManager: AuthPreferencesManager
) : Interceptor {

    companion object {
        private const val CURRENT_DOMAIN = BuildConfig.API_DOMAIN

        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_AUTHORIZATION_BEARER = "Bearer"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = authPreferencesManager.token
        return chain.request().newBuilder()
            .header(HEADER_AUTHORIZATION, "$HEADER_AUTHORIZATION_BEARER $token")
            .build()
            .let { chain.proceed(it) }
    }

}