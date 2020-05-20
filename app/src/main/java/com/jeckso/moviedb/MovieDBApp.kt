package com.jeckso.moviedb

import android.app.Application
import com.jeckso.moviedb.dagger.ApplicationComponent
import com.jeckso.moviedb.dagger.ApplicationModule
import com.jeckso.moviedb.dagger.DaggerApplicationComponent
import timber.log.Timber

class MovieDBApp : Application() {

    companion object {
    }
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}