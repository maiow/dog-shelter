package com.redpine.home.presentation.home


import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.redpine.core.model.card.Item

open class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        (oldItem.id == newItem.id) && (oldItem.isFavorite == newItem.isFavorite)

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
        return if (oldItem.isFavorite != newItem.isFavorite) {
            Bundle().apply {
                putString("key", "isFavorite")
            }
        } else super.getChangePayload(oldItem, newItem)

    }
}
