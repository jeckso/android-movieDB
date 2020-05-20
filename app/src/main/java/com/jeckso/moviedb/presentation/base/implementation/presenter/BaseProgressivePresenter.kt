package com.jeckso.moviedb.presentation.base.implementation.presenter

import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseProgressivePresenter<T : BaseProgressiveView> : BasePresenter<T>() {

    fun executeOn(
        coroutineContext: CoroutineContext = Dispatchers.Main,
        block: suspend () -> Unit
    ) {
        launch(coroutineContext) { block() }
    }

    suspend fun <T> execute(
        coroutineContext: CoroutineContext = Dispatchers.IO,
        block: suspend () -> T
    ): T {
        return try {
            viewState.showProgress()
            withContext(coroutineContext) { block() }
        } finally {
            viewState.hideProgress()
        }
    }

    fun <T> Flow<T>.execute(action: suspend (T) -> Unit): Job {
        this.onStart { viewState.showProgress() }
        this.flowOn(Dispatchers.Main)
        this.onEach(action)
        this.flowOn(Dispatchers.Main)
        this.onCompletion { viewState.hideProgress() }
        this.flowOn(Dispatchers.Main)
        return launchIn(this@BaseProgressivePresenter)
    }
}