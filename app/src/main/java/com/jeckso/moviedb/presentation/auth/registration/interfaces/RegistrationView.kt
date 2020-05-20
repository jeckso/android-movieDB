package com.jeckso.moviedb.presentation.auth.registration.interfaces

import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(OneExecutionStateStrategy::class)
interface RegistrationView : BaseProgressiveView {

    fun onRegistrationSuccess()
}