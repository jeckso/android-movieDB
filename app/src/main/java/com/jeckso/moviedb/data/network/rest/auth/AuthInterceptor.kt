package com.jeckso.moviedb.data.network.rest.auth

import com.jeckso.moviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val CURRENT_DOMAIN = BuildConfig.API_DOMAIN

        private const val POST = "POST"
        private const val PATCH = "PATCH"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_AUTHORIZATION_BEARER = "Bearer"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = ""
        return chain.request().newBuilder()
            .header(HEADER_AUTHORIZATION, "$HEADER_AUTHORIZATION $token")
            .build()
            .let { chain.proceed(it) }
    }

}