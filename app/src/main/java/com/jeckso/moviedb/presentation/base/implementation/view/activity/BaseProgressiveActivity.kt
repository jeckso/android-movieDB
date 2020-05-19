package com.jeckso.moviedb.presentation.base.implementation.view.activity

import android.os.Bundle
import androidx.core.widget.ContentLoadingProgressBar
import com.jeckso.moviedb.R
import com.jeckso.moviedb.presentation.base.interfaces.BaseProgressiveView

abstract class BaseProgressiveActivity : BaseActivity(), BaseProgressiveView {

    private lateinit var contentLoadingProgressBar: ContentLoadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentLoadingProgressBar = findViewById(R.id.layout_progress)
    }

    override fun showProgress() {
        contentLoadingProgressBar.show()
    }

    override fun hideProgress() {
        contentLoadingProgressBar.hide()
    }

}