package com.redpine.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class ListDelegateAdapter<Interface>(
    diffUtil: DiffUtil.ItemCallback<Interface>,
) : ListAdapter<Interface, RecyclerView.ViewHolder>(diffUtil) {

    private val delegateManager = AdaptersDelegateManager<Interface>()

    fun addDelegate(delegate: AdapterDelegate<Interface, >) {
        delegateManager.addDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return delegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateManager.onBindViewHolder(getItem(position), holder)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder, position)
        else delegateManager.onBindViewHolder(getItem(position), holder, payloads)
    }
}