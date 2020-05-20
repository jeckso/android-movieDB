package com.jeckso.moviedb.presentation.main

import android.os.Bundle
import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseProgressiveActivity
import com.jeckso.moviedb.presentation.main.films.implementation.FilmListFragment

class MainActivity : BaseProgressiveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(FilmListFragment())
    }

}