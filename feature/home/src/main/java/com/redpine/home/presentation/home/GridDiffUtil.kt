package com.redpine.home.presentation.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.redpine.home.domain.model.grid.Grid

class GridDiffUtil : DiffUtil.ItemCallback<Grid>() {

    override fun areItemsTheSame(oldItem: Grid, newItem: Grid): Boolean {
        return oldItem.titleId == newItem.titleId
    }

    override fun areContentsTheSame(oldItem: Grid, newItem: Grid): Boolean {
        return (oldItem.titleId == newItem.titleId) &&
                (oldItem.list == newItem.list) &&
                (oldItem.orientation == newItem.orientation) &&
                (oldItem.spanCount == newItem.spanCount)
    }

    override fun getChangePayload(oldItem: Grid, newItem: Grid): Any? {
        Log.d(TAG, "oldItem: $oldItem")
        Log.d(TAG, "newItem: $newItem")
        return if (oldItem.list.map { it.isFavorite } != newItem.list.map { it.isFavorite }) {
            Bundle().apply { putString("key", "isFavoriteInList") }
        } else super.getChangePayload(oldItem, newItem)
    }
}
