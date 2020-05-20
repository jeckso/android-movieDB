package com.jeckso.moviedb.data.manager

import android.content.Context
import com.jeckso.moviedb.BuildConfig
import com.securepreferences.SecurePreferences
import javax.inject.Inject

class AuthPreferencesManager @Inject constructor(
    context: Context
) : PreferencesManager(SecurePreferences(context, BuildConfig.SECURE_NAME)) {

    companion object {
        private const val TOKEN_KEY = "auth.token"
    }

    var token: String
        set(value) {
            edit { putString(TOKEN_KEY, value) }
        }
        get() = getString(TOKEN_KEY, "") ?: ""
}