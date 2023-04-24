package com.redpine.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class PagingDelegateAdapter<Item : Any>(
    diffUtil: DiffUtil.ItemCallback<Item>,
) : PagingDataAdapter<Item, RecyclerView.ViewHolder>(diffUtil) {

    private val delegateManager = AdaptersDelegateManager<Item>()

    fun addDelegate(delegate: AdapterDelegate<Item>) {
        delegateManager.addDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(snapshot().items[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { delegateManager.onBindViewHolder(it, holder) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }
}