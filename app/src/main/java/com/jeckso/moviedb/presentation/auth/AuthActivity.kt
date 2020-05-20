package com.jeckso.moviedb.presentation.auth

import android.os.Bundle
import com.jeckso.moviedb.R
import com.jeckso.moviedb.presentation.auth.login.implementation.LoginFragment
import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseProgressiveActivity

class AuthActivity : BaseProgressiveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(LoginFragment())
    }
}