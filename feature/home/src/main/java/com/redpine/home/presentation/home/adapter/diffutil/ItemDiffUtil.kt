package com.redpine.home.presentation.home.adapter.diffutil

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.redpine.core.model.card.Item

class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
        return if (oldItem.isFavorite != newItem.isFavorite) {
            newItem.isFavorite
        } else super.getChangePayload(oldItem, newItem)
    }
}
