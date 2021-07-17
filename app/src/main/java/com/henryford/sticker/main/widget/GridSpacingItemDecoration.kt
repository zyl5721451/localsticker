package com.henryford.sticker.main.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridSpacingItemDecoration : RecyclerView.ItemDecoration {

    private var spanCount = 0//列数

    private var spacing = 0//间隔

    private var isIncludeEdge = false//是否有边缘

    constructor(spanCount: Int, spacing: Int, isIncludeEdge: Boolean) : super() {
        this.spanCount = spanCount
        this.spacing = spacing
        this.isIncludeEdge = isIncludeEdge
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        if (isIncludeEdge) {
            outRect.left =
                spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right =
                spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }

    }
}