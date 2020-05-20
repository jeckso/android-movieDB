package com.jeckso.moviedb.dagger.modules

import android.content.Context
import com.jeckso.moviedb.data.manager.AuthPreferencesManager
import com.jeckso.moviedb.data.network.rest.RestServiceFactory
import com.jeckso.moviedb.data.network.rest.auth.AuthInterceptor
import com.jeckso.moviedb.data.network.rest.auth.AuthenticatorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun authPreferencesManager(context: Context) : AuthPreferencesManager {
        return AuthPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun authenticator(context: Context) : AuthenticatorImpl {
        return AuthenticatorImpl(context)
    }

    @Provides
    @Singleton
    fun authInterceptor(authPreferencesManager: AuthPreferencesManager) : AuthInterceptor {
        return AuthInterceptor(authPreferencesManager)
    }

    @Provides
    @Singleton
    fun restServiceFactory(
        authInterceptor: AuthInterceptor,
        authenticatorImpl: AuthenticatorImpl
    ) : RestServiceFactory {
        return RestServiceFactory(authInterceptor, authenticatorImpl)
    }
}