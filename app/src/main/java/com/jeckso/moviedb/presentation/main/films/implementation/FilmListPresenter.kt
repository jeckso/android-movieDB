package com.jeckso.moviedb.presentation.main.films.implementation

import com.jeckso.moviedb.R
import com.jeckso.moviedb.data.repository.films.FilmsRepository
import com.jeckso.moviedb.data.repository.films.specification.GetAllFilmsSpec
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
    private val filmsRepository: FilmsRepository
) : BaseProgressivePresenter<FilmListView>() {

    private var currentJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadFilms()
    }

    fun navigateToFilm(filmVM: FilmVM) {
        viewState.navigateToFilm(filmVM)
    }

    private fun loadFilms() {
        currentJob?.cancel()
        currentJob = filmsRepository.retrieve(GetAllFilmsSpec)
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