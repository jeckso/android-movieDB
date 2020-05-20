package com.jeckso.moviedb.presentation.auth

import android.content.Intent
import android.os.Bundle
import com.jeckso.moviedb.presentation.auth.login.implementation.LoginFragment
import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseProgressiveActivity
import com.jeckso.moviedb.presentation.main.MainActivity

class AuthActivity : BaseProgressiveActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(LoginFragment())
    }

    fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}