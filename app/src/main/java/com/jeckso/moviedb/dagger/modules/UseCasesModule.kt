package com.jeckso.moviedb.dagger.modules

import com.jeckso.moviedb.data.manager.AuthPreferencesManager
import com.jeckso.moviedb.data.network.rest.RestServiceFactory
import com.jeckso.moviedb.data.network.rest.service.AuthService
import com.jeckso.moviedb.domain.usecases.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun authUseCase(
        authPreferencesManager: AuthPreferencesManager,
        restServiceFactory: RestServiceFactory
    ): AuthUseCase {
        val service = restServiceFactory.createService(AuthService::class.java)
        return AuthUseCase(service, authPreferencesManager)
    }
}