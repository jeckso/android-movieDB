package com.jeckso.moviedb.data.repository.films

import com.jeckso.moviedb.data.models.FilmResponseModel
import com.jeckso.moviedb.data.network.rest.service.FilmService
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.domain.repository.BaseDataSource
import com.jeckso.moviedb.domain.repository.DeleteSpec
import com.jeckso.moviedb.domain.repository.RetrieveSpec
import com.jeckso.moviedb.domain.repository.UpdateSpec
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val filmService: FilmService
) : BaseDataSource<Nothing, FilmResponseModel> {

    override suspend fun create(item: Nothing): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }

    override fun retrieve(spec: RetrieveSpec): Flow<List<FilmResponseModel>> = flow {
        emit(filmService.getAllFilms(mapOf()))
    }

    override suspend fun update(spec: UpdateSpec): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }

    override suspend fun delete(spec: DeleteSpec): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }


}