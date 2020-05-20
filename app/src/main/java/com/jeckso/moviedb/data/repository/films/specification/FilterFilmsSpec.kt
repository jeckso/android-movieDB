package com.jeckso.moviedb.data.repository.films.specification

import com.jeckso.moviedb.domain.repository.RetrieveSpec

data class FilterFilmsSpec(
    val title: String,
    val genre: String,
    val rating: Int
) : RetrieveSpec()