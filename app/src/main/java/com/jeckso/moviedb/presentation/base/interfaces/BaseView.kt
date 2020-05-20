package com.jeckso.moviedb.presentation.base.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseView : MvpView {

    fun showMessage(title: String, message: String)

    fun showMessage(title: Int, message: Int)

    fun showMessage(title: Int, message: String)

    fun showMessage(title: String, message: Int)
}