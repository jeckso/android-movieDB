package com.jeckso.moviedb.android.list.decorators

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PositionMarginDecorator(
    private val position: Int,
    private val marginRect: Rect
) : RecyclerView.ItemDecoration() {

    constructor(
        position: Int,
        start: Int = 0, top: Int = 0,
        bottom: Int = 0, end: Int = 0
    ) : this(position, Rect(start, top, end, bottom))

    companion object {
        const val FIRST = 0
        const val LAST = -2
    }

    @SuppressLint("CheckResult")
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val isCurrent = when (this.position) {
            LAST -> position == parent.adapter!!.itemCount - 1
            else -> position == this.position
        }
        outRect.takeIf { isCurrent }?.set(marginRect)
    }
}