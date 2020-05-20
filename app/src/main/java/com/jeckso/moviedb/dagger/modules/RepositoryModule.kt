package com.jeckso.moviedb.dagger.modules

import com.jeckso.moviedb.data.network.rest.RestServiceFactory
import com.jeckso.moviedb.data.network.rest.service.FilmService
import com.jeckso.moviedb.data.network.rest.service.GenreService
import com.jeckso.moviedb.data.repository.films.FilmsRepository
import com.jeckso.moviedb.data.repository.genres.GenresRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun filmsRepository(restServiceFactory: RestServiceFactory): FilmsRepository {
        val service = restServiceFactory.createService(FilmService::class.java)
        return FilmsRepository(service)
    }

    @Provides
    @Singleton
    fun genresRepository(restServiceFactory: RestServiceFactory): GenresRepository {
        val service = restServiceFactory.createService(GenreService::class.java)
        return GenresRepository(service)
    }
}