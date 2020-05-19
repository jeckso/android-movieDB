package com.jeckso.moviedb.presentation.base.interfaces

import moxy.MvpView

interface BaseView : MvpView {

    fun showMessage(title: String, message: String)

    fun showMessage(title: Int, message: Int)

    fun showMessage(title: Int, message: String)

    fun showMessage(title: String, message: Int)
}