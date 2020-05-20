package com.jeckso.moviedb.dagger

import android.content.Context
import com.jeckso.moviedb.dagger.modules.NetworkModule
import com.jeckso.moviedb.dagger.modules.RepositoryModule
import com.jeckso.moviedb.dagger.modules.UseCasesModule
import com.jeckso.moviedb.presentation.auth.login.implementation.LoginFragment
import com.jeckso.moviedb.presentation.auth.registration.implementation.RegistrationFragment
import com.jeckso.moviedb.presentation.main.films.implementation.FilmListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class, UseCasesModule::class]
)
interface ApplicationComponent {

    fun context(): Context

    fun inject(loginFragment: LoginFragment)

    fun inject(filmListFragment: FilmListFragment)

    fun inject(registrationFragment: RegistrationFragment)
}