package com.jeckso.moviedb.presentation.main.films.implementation

import com.jeckso.moviedb.R
import com.jeckso.moviedb.data.repository.films.FilmsRepository
import com.jeckso.moviedb.data.repository.films.specification.FilterFilmsSpec
import com.jeckso.moviedb.data.repository.films.specification.GetAllGenresSpec
import com.jeckso.moviedb.data.repository.genres.GenresRepository
import com.jeckso.moviedb.domain.repository.RetrieveSpec
import com.jeckso.moviedb.mappers.EntityToVMMapper
import com.jeckso.moviedb.presentation.base.implementation.presenter.BaseProgressivePresenter
import com.jeckso.moviedb.presentation.main.films.interfaces.FilmListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FilmListPresenter @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val genresRepository: GenresRepository
) : BaseProgressivePresenter<FilmListView>() {

    var filmName: String = ""
    var genre: String = ""
    var rating: Int = 0
        set(value) {
            field = value
            Timber.e("RATE $value")
        }

    private var currentJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        search(GetAllGenresSpec)
    }

    fun navigateToFilm(filmVM: FilmVM) {
        viewState.navigateToFilm(filmVM)
    }

    fun showSearchDialog() {
        genresRepository.retrieve(GetAllGenresSpec)
            .map { list -> list.map { it.name }}
            .flowOn(Dispatchers.IO)
            .onStart { viewState.showProgress() }
            .onCompletion { viewState.hideProgress() }
            .onEach {
                Timber.e("$it")
                viewState.showSearchDialog(it)
            }.launchIn(this)
    }

    fun clearFiltering() {
        filmName = ""
        genre = ""
        rating = -1
        search(GetAllGenresSpec)
    }

    fun search() {
        search(FilterFilmsSpec(filmName, genre, rating))
    }

    private fun search(retrieveSpec: RetrieveSpec) {
        currentJob?.cancel()
        currentJob = filmsRepository.retrieve(retrieveSpec)
            .map { list -> list.map { EntityToVMMapper.map(it) } }
            .flowOn(Dispatchers.IO)
            .onStart { viewState.showProgress() }
            .onCompletion { viewState.hideProgress() }
            .catch {
                val message = it.message
                if (message.isNullOrBlank()) {
                    viewState.showMessage(R.string.error, R.string.error_content_undefined)
                } else {
                    viewState.showMessage(R.string.error, message)
                }
            }
            .onEach {
                if (it.isEmpty()) {
                    viewState.onFilmsNotFound()
                } else {
                    viewState.onFilmsReady(it)
                }
            }
            .flowOn(Dispatchers.Main)
            .launchIn(this)
    }
}