package com.jeckso.moviedb.presentation.auth.registration.implementation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.jeckso.moviedb.R
import com.jeckso.moviedb.presentation.auth.AuthActivity
import com.jeckso.moviedb.presentation.auth.registration.interfaces.RegistrationView
import com.jeckso.moviedb.presentation.base.implementation.view.fragment.BaseProgressiveFragment
import moxy.ktx.moxyPresenter
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class RegistrationFragment : BaseProgressiveFragment(), RegistrationView {

    @Inject
    lateinit var provider: Provider<RegistrationPresenter>
    private val presenter by moxyPresenter { provider.get() }

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    override val layoutResId: Int = R.layout.fragment_sign_up

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEditText = view.findViewById(R.id.input_email)
        passwordEditText = view.findViewById(R.id.input_password)
        confirmPasswordEditText = view.findViewById(R.id.input_password_confirm)
        val registerButton: View = view.findViewById(R.id.button_register)
        registerButton.setOnClickListener {
            presenter.register(
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                confirmPasswordEditText.text.toString()
            )
        }
    }

    override fun onRegistrationSuccess() {
        (activity as? AuthActivity)?.navigateToMainScreen()
    }

}