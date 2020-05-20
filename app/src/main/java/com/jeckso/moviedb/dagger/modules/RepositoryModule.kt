package com.jeckso.moviedb.dagger.modules

import com.jeckso.moviedb.data.manager.AuthPreferencesManager
import com.jeckso.moviedb.data.network.rest.RestServiceFactory
import com.jeckso.moviedb.data.network.rest.service.AuthService
import com.jeckso.moviedb.data.network.rest.service.FilmService
import com.jeckso.moviedb.data.repository.films.FilmsRepository
import com.jeckso.moviedb.domain.usecases.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun repositoryModule(restServiceFactory: RestServiceFactory): FilmsRepository {
        val service = restServiceFactory.createService(FilmService::class.java)
        return FilmsRepository(service)
    }
}