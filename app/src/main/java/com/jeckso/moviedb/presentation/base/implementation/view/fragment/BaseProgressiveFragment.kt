package com.jeckso.moviedb.presentation.base.implementation.view.fragment

import com.jeckso.moviedb.presentation.base.implementation.view.activity.BaseProgressiveActivity
import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView

abstract class BaseProgressiveFragment : BaseFragment(), BaseProgressiveView {

    override fun showProgress() {
        (activity as? BaseProgressiveActivity)?.showProgress()
    }

    override fun hideProgress() {
        (activity as? BaseProgressiveActivity)?.hideProgress()
    }
}