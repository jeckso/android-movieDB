package com.jeckso.moviedb.presentation.auth.login.implementation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.jeckso.moviedb.MovieDBApp
import com.jeckso.moviedb.R
import com.jeckso.moviedb.presentation.auth.login.interfaces.LoginView
import com.jeckso.moviedb.presentation.auth.registration.implementation.RegistrationFragment
import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseActivity
import com.jeckso.moviedb.presentation.base.implementation.view.fragment.BaseProgressiveFragment
import moxy.ktx.moxyPresenter
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseProgressiveFragment(), LoginView {

    @Inject
    lateinit var clientDetailsPresenter: Provider<LoginPresenter>
    private val presenter by moxyPresenter { clientDetailsPresenter.get() }

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override val layoutResId: Int = R.layout.fragment_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEditText = view.findViewById(R.id.input_email)
        passwordEditText = view.findViewById(R.id.input_password)
        val loginButton: View = view.findViewById(R.id.button_login)
        loginButton.setOnClickListener {
            presenter.authorize(emailEditText.text.toString(), passwordEditText.text.toString())
        }
        val registerButton: View = view.findViewById(R.id.button_register)
        registerButton.setOnClickListener {
            presenter.navigateToRegistration()
        }
    }

    override fun navigateToMainScreen() {
        Timber.e("MAIN SCREEN")
    }

    override fun navigateToRegistration() {
        (activity as? BaseActivity)?.showFragment(RegistrationFragment(), true)
    }

}