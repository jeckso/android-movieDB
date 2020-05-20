package com.jeckso.moviedb.presentation.auth.login.implementation

import com.jeckso.moviedb.R
import com.jeckso.moviedb.data.utils.result.ErrorResult
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.data.utils.result.Success
import com.jeckso.moviedb.domain.usecases.AuthUseCase
import com.jeckso.moviedb.presentation.auth.login.interfaces.LoginView
import com.jeckso.moviedb.presentation.base.implementation.presenter.BaseProgressivePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(
    private val authUseCase: AuthUseCase
) : BaseProgressivePresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (!authUseCase.token.isNullOrBlank()) {
            viewState.navigateToMainScreen()
        }
    }

    fun authorize(email: String, password: String) = executeOn {
        val result: PendingResult<*> = execute { authUseCase.authorize(email, password) }
        when (result) {
            is Success<*> -> viewState.navigateToMainScreen()
            is ErrorResult -> viewState.showMessage(R.string.error, result.message)
        }
    }

    fun navigateToRegistration() {
        viewState.navigateToRegistration()
    }
}