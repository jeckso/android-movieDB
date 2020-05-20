package com.jeckso.moviedb.presentation.base.implementation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.jeckso.moviedb.MovieDBApp
import com.jeckso.moviedb.dagger.ApplicationComponent
import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseActivity
import com.jeckso.moviedb.presentation.base.interfaces.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun showMessage(title: String, message: String) {
        (activity as? BaseActivity)?.showMessage(title, message)
    }

    override fun showMessage(title: Int, message: Int) {
        (activity as? BaseActivity)?.showMessage(title, message)
    }

    override fun showMessage(title: Int, message: String) {
        (activity as? BaseActivity)?.showMessage(title, message)
    }

    override fun showMessage(title: String, message: Int) {
        (activity as? BaseActivity)?.showMessage(title, message)
    }

    protected val applicationComponent
        get() = (context?.applicationContext as MovieDBApp).applicationComponent

    protected abstract val layoutResId: Int
        @LayoutRes get
}