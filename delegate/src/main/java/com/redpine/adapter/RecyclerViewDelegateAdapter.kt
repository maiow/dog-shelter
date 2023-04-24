package com.redpine.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

abstract class RecyclerViewDelegateAdapter<Item> : Adapter<RecyclerView.ViewHolder>() {

    private val delegateManager = AdaptersDelegateManager<Item>()

    fun addDelegate(delegate: AdapterDelegate<Item>) {
        delegateManager.addDelegate(delegate)
    }

    private var items: List<Item> = emptyList()

    fun submitList(list: List<Item>) {
        items = list
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(items[position])
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateManager.onBindViewHolder(items[position], holder)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder, position)
        else delegateManager.onBindViewHolder(items[position], holder, payloads)
    }
}