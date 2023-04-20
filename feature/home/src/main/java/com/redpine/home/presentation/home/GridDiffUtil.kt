package com.redpine.home.presentation.home

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
        return if (oldItem.list.map { it.isFavorite } != newItem.list.map { it.isFavorite }) {
            LIST_UPDATE
        } else super.getChangePayload(oldItem, newItem)
    }

    companion object {
        const val LIST_UPDATE = "list_update"
    }
}
