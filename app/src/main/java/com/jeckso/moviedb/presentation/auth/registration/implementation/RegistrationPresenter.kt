package com.jeckso.moviedb.presentation.auth.registration.implementation

import android.util.Patterns
import com.jeckso.moviedb.R
import com.jeckso.moviedb.data.utils.result.ErrorResult
import com.jeckso.moviedb.data.utils.result.PendingResult
import com.jeckso.moviedb.data.utils.result.Success
import com.jeckso.moviedb.domain.usecases.AuthUseCase
import com.jeckso.moviedb.presentation.auth.registration.interfaces.RegistrationView
import com.jeckso.moviedb.presentation.base.implementation.presenter.BaseProgressivePresenter
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter @Inject constructor(
    private val authUseCase: AuthUseCase
) : BaseProgressivePresenter<RegistrationView>() {

    fun register(email: String, password: String, confirmation: String) = executeOn {
        if (!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
            viewState.showMessage(R.string.error, R.string.error_content_irregular_email)
            return@executeOn
        }
        if (password != confirmation) {
            viewState.showMessage(R.string.error, R.string.error_content_password_confirmation)
            return@executeOn
        }
        val result: PendingResult<*> = execute { registerInternally(email, password) }
        Timber.e("$result")
        when (result) {
            is Success<*> -> viewState.onRegistrationSuccess()
            is ErrorResult -> viewState.showMessage(R.string.error, result.message)
        }
    }

    private suspend fun registerInternally(
        email: String,
        password: String
    ): PendingResult<*> {
        val result: PendingResult<*> = authUseCase.register(email, password)
        return when (result) {
            is Success<*> -> authUseCase.authorize(email, password)
            is ErrorResult -> result
        }
    }
}