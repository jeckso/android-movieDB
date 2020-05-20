package com.jeckso.moviedb.mappers

import com.jeckso.moviedb.data.models.FilmResponseModel
import com.jeckso.moviedb.presentation.main.films.implementation.FilmVM

object EntityToVMMapper {

    fun map(filmResponseModel: FilmResponseModel) : FilmVM = with(filmResponseModel) {
        FilmVM(id, title, rating, posterImage, overview, releaseDate, genres)
    }
}