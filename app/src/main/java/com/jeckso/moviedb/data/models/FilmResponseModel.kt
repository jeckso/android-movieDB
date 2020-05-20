package com.jeckso.moviedb.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class FilmResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Int,
    @SerializedName("poster_path")
    val posterImage: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("genre")
    val genres: List<GenreResponseModel>
)