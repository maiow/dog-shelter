package com.redpine.home.presentation.home


import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.redpine.core.model.card.Item

open class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        oldItem.id == newItem.id
}