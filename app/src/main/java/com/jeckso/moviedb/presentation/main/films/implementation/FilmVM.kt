package com.jeckso.moviedb.presentation.main.films.implementation

import com.jeckso.moviedb.data.models.GenreResponseModel
import java.util.*

class FilmVM(
    val id: Int,
    val title: String,
    val rating: Int,
    val posterImage: String,
    val overview: String,
    val releaseDate: Date,
    val genres: List<GenreResponseModel>
)