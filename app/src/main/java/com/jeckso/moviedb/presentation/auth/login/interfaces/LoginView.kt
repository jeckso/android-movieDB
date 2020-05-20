package com.jeckso.moviedb.presentation.auth.login.interfaces

import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoginView : BaseProgressiveView {

    fun navigateToMainScreen()

    fun navigateToRegistration()
}