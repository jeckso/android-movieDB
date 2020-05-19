package com.jeckso.moviedb.presentation.base.implementation.presenter

import com.jeckso.moviedb.presentation.base.interfaces.BaseView
import com.jeckso.moviedb.utils.coroutines.ExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T: BaseView> : MvpPresenter<T>(), CoroutineScope {

    private val rootJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + ExceptionHandler + rootJob

    override fun onDestroy() {
        super.onDestroy()
        rootJob.cancel()
    }
}