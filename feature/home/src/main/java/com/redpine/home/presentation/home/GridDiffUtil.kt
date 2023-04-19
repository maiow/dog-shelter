package com.redpine.home.presentation.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid

class GridDiffUtil : DiffUtil.ItemCallback<Grid>() {

    override fun areItemsTheSame(oldItem: Grid, newItem: Grid): Boolean =
        oldItem.titleId == newItem.titleId

    override fun areContentsTheSame(oldItem: Grid, newItem: Grid): Boolean =
        (oldItem.titleId == newItem.titleId) && (oldItem.list == newItem.list)

    override fun getChangePayload(oldItem: Grid, newItem: Grid): Any? {
        Log.d(TAG, "oldItem: $oldItem")
        Log.d(TAG, "newItem: $newItem")
        return when {
            oldItem is HorizontalGrid && newItem is HorizontalGrid -> {
                if ((oldItem.list.map { it.isFavorite } != newItem.list.map { it.isFavorite }) && oldItem.list.size == newItem.list.size) {
                    Bundle().apply {
                        putString("key", "isFavoriteInList")
                    }
                } else super.getChangePayload(oldItem, newItem)
            }
            else -> super.getChangePayload(oldItem, newItem)
        }
    }
}