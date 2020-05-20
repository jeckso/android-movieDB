package com.jeckso.moviedb.data.manager

import android.content.SharedPreferences

abstract class PreferencesManager(private val preferences: SharedPreferences) : SharedPreferences by preferences {

    companion object {
        const val NO_STRING_VALUE = ""
        const val NO_ID = -1
    }

    protected fun edit(editor: SharedPreferences.Editor.() -> Unit) {
        preferences.edit().also(editor).commit()
    }
}