package com.jeckso.moviedb.presentation.base.implementation.view.fragment

import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseActivity
import com.jeckso.moviedb.presentation.base.interfaces.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

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
}