package com.jeckso.moviedb.data.network.rest.service

import com.jeckso.moviedb.data.models.FilmResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface FilmService {
    companion object {

        private const val ENDPOINT = "/movies"
    }

    @GET(ENDPOINT)
    suspend fun getAllFilms(@QueryMap map: Map<String, Any?>): List<FilmResponseModel>
}