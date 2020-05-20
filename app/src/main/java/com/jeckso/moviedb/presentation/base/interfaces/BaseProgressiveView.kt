package com.jeckso.moviedb.presentation.base.interfaces

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseProgressiveView : BaseView {

    fun showProgress()

    fun hideProgress()
}