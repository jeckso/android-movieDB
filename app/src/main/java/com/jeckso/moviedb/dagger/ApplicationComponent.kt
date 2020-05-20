package com.jeckso.moviedb.dagger

import android.content.Context
import com.jeckso.moviedb.dagger.modules.NetworkModule
import com.jeckso.moviedb.dagger.modules.UseCasesModule
import com.jeckso.moviedb.presentation.auth.login.implementation.LoginFragment
import com.jeckso.moviedb.presentation.auth.registration.implementation.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, UseCasesModule::class]
)
interface ApplicationComponent {

    fun context(): Context

    fun inject(loginFragment: LoginFragment)

    fun inject(registrationFragment: RegistrationFragment)
}