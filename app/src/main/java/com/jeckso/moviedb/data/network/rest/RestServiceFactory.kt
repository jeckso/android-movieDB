package com.jeckso.moviedb.data.network.rest

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jeckso.moviedb.BuildConfig
import com.jeckso.moviedb.data.network.rest.auth.AuthInterceptor
import com.jeckso.moviedb.data.network.rest.auth.AuthenticatorImpl
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestServiceFactory @Inject constructor(
    private val authInterceptor: AuthInterceptor,
    private val authenticator: AuthenticatorImpl
) {

    companion object {
        private const val CONNECTION_TIMEOUT = 60L
        private const val READ_TIMEOUT = CONNECTION_TIMEOUT
        private const val WRITE_TIMEOUT = CONNECTION_TIMEOUT
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .apply {
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .build()
    }

    val GSON = GsonBuilder()
        .setPrettyPrinting()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
    }

    fun <T> createService(clazz: Class<T>) = retrofit.create(clazz)
}