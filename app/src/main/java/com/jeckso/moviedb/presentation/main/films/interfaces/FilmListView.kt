package com.jeckso.moviedb.presentation.main.films.interfaces

import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView
import com.jeckso.moviedb.presentation.main.films.implementation.FilmVM
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface FilmListView : BaseProgressiveView {

    fun onFilmsReady(list: List<FilmVM>)

    fun onFilmsNotFound()

    fun navigateToFilm(filmVM: FilmVM)
}