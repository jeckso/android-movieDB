package com.jeckso.moviedb.android.list

interface ItemClickListener<T> {

    fun onItemClick(item: T, position: Int)

}