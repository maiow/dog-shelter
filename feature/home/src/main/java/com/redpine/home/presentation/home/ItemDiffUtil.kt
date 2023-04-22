package com.redpine.home.presentation.home

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
            FAVORITE_BUTTON_CLICKED
        } else super.getChangePayload(oldItem, newItem)
    }

    companion object {
        const val FAVORITE_BUTTON_CLICKED = "FavoriteButtonClicked"
    }
}
