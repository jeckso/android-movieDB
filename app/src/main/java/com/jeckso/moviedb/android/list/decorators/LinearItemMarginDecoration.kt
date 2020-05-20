package com.jeckso.moviedb.android.list.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearItemMarginDecoration(
    orientation: Int = LinearLayoutManager.VERTICAL,
    space: Int,
    private val outBound: Boolean = false
) : RecyclerView.ItemDecoration() {

    companion object {
        const val BEGIN = 0
    }

    private val margins = when (orientation) {
        LinearLayoutManager.VERTICAL -> space to 0
        else -> 0 to space
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        with(outRect) {

            top = margins.first / 2
            left = margins.second / 2
            right = margins.second / 2
            bottom = margins.first / 2

            when (outBound to position) {
                false to BEGIN -> {
                    top = 0
                    right = 0
                }
                false to parent.adapter!!.itemCount - 1 -> {
                    bottom = 0
                    left = 0
                }
            }
        }
    }
}