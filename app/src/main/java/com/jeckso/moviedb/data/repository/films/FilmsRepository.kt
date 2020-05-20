package com.jeckso.moviedb.data.repository.films

import com.jeckso.moviedb.data.models.FilmResponseModel
import com.jeckso.moviedb.data.network.rest.service.FilmService
import com.jeckso.moviedb.data.repository.films.specification.FilterFilmsSpec
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

    companion object {
        private const val KEY_TITLE = "name"
        private const val KEY_GENRE = "genre"
        private const val KEY_RATING = "rating"
    }

    override suspend fun create(item: Nothing): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }

    override fun retrieve(spec: RetrieveSpec): Flow<List<FilmResponseModel>> = flow {
        val map = mutableMapOf<String, Any?>()
        when (spec) {
            is FilterFilmsSpec -> {
                if (spec.title.isNotEmpty()) {
                    map[KEY_TITLE] = spec.title
                }
                if (spec.genre.isNotEmpty()) {
                    map[KEY_GENRE] = spec.genre
                }
                if (spec.rating != -1) {
                    map[KEY_RATING] = spec.rating
                }
            }
        }
        emit(filmService.getAllFilms(map))
    }

    override suspend fun update(spec: UpdateSpec): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }

    override suspend fun delete(spec: DeleteSpec): PendingResult<FilmResponseModel> {
        throw NotImplementedError()
    }


}