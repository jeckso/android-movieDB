package com.jeckso.moviedb.utils.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

object ExceptionHandler : CoroutineExceptionHandler {

    private val delegate = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    override val key: CoroutineContext.Key<*>
        get() = delegate.key

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        delegate.handleException(context, exception)
    }
}