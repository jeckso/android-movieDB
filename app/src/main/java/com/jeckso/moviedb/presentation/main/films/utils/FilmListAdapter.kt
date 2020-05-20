package com.jeckso.moviedb.presentation.main.films.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jeckso.moviedb.R
import com.jeckso.moviedb.android.list.BaseRecyclerViewAdapter
import com.jeckso.moviedb.android.list.ItemClickListener
import com.jeckso.moviedb.presentation.main.films.implementation.FilmVM

class FilmListAdapter(
    listener: ItemClickListener<FilmVM>
): BaseRecyclerViewAdapter<FilmVM, FilmVH>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmVH {
        val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_film, parent, false)
        return FilmVH(view)
    }
}