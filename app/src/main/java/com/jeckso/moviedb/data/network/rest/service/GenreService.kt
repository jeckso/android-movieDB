package com.jeckso.moviedb.data.network.rest.service

import com.jeckso.moviedb.data.models.GenreResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface GenreService {
    companion object {
        private const val ENDPOINT = "/genres"
    }

    @GET(ENDPOINT)
    suspend fun getAllGenres(@QueryMap map: Map<String, Any?>): List<GenreResponseModel>
}