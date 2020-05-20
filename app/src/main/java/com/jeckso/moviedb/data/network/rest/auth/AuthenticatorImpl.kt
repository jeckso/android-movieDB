package com.jeckso.moviedb.data.network.rest.auth

import android.content.Context
import android.content.Intent
import com.jeckso.moviedb.presentation.auth.AuthActivity
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorImpl @Inject constructor(private val context: Context) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        var flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        flags = flags or Intent.FLAG_ACTIVITY_SINGLE_TOP
        flags = flags or Intent.FLAG_ACTIVITY_CLEAR_TASK
        flags = flags or Intent.FLAG_ACTIVITY_NEW_TASK
        val intent = Intent(context, AuthActivity::class.java).also {
            it.flags = flags
        }
        context.startActivity(intent)
        return null
    }
}