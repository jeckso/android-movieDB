package com.jeckso.moviedb.data.repository.genres

import com.jeckso.moviedb.data.models.FilmResponseModel
import com.jeckso.moviedb.data.models.GenreResponseModel
import com.jeckso.moviedb.data.network.rest.service.FilmService
import com.jeckso.moviedb.data.network.rest.service.GenreService
import com.jeckso.moviedb.data.repository.films.specification.FilterFilmsSpec
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.domain.repository.BaseDataSource
import com.jeckso.moviedb.domain.repository.DeleteSpec
import com.jeckso.moviedb.domain.repository.RetrieveSpec
import com.jeckso.moviedb.domain.repository.UpdateSpec
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenresRepository @Inject constructor(
    private val genreService: GenreService
) : BaseDataSource<Nothing, GenreResponseModel> {

    override suspend fun create(item: Nothing): PendingResult<GenreResponseModel> {
        throw NotImplementedError()
    }

    override fun retrieve(spec: RetrieveSpec): Flow<List<GenreResponseModel>> = flow {
        emit(genreService.getAllGenres(emptyMap()))
    }

    override suspend fun update(spec: UpdateSpec): PendingResult<GenreResponseModel> {
        throw NotImplementedError()
    }

    override suspend fun delete(spec: DeleteSpec): PendingResult<GenreResponseModel> {
        throw NotImplementedError()
    }


}