package com.redpine.core.tools

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val marginHorizontal: Int, private val marginVertical: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = marginVertical
            bottom = marginVertical
            right = marginHorizontal
            left = marginHorizontal
        }
    }

}