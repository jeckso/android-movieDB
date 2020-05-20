package com.jeckso.moviedb.android.list.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingItemDecorator(private val bounds: Rect) : RecyclerView.ItemDecoration() {

    constructor(vertical: Int, horizontal: Int) : this(
        Rect(
            horizontal / 2,
            vertical / 2,
            horizontal / 2,
            vertical / 2
        )
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(bounds)
    }
}