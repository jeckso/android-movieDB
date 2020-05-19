package com.jeckso.moviedb.data.network.rest.auth

import android.content.Context
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorImpl @Inject constructor(private val context: Context) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // context.startActivity() logout, navigate to login
        return null
    }
}