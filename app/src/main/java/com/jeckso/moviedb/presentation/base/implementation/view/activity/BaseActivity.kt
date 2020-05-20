package com.jeckso.moviedb.presentation.base.implementation.view.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.afollestad.materialdialogs.MaterialDialog
import com.jeckso.moviedb.R
import com.jeckso.moviedb.presentation.base.implementation.view.fragment.BaseFragment
import com.jeckso.moviedb.presentation.base.interfaces.BaseView
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    protected var currentFragment: BaseFragment? = null

    protected abstract val layoutResId: Int @LayoutRes get

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }

    override fun showMessage(title: String, message: String) {
        MaterialDialog(this).show {
            title(text = title)
            message(text = message)
            positiveButton { it.dismiss() }
        }
    }

    override fun showMessage(title: Int, message: Int) {
        showMessage(getString(title), getString(message))
    }

    override fun showMessage(title: Int, message: String) {
        showMessage(getString(title), message)
    }

    override fun showMessage(title: String, message: Int) {
        showMessage(title, getString(message))
    }

    fun showFragment(fragment: BaseFragment, addToBackStack: Boolean = false) {
        val tag = fragment.javaClass.canonicalName
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layout_fragment_holder, fragment, tag)
        transaction.takeIf { addToBackStack }?.addToBackStack(tag)
        transaction.commit()
        currentFragment = fragment
    }
}