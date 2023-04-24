package com.redpine.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class DslListAdapterDelegate<Item : Interface, Interface, VB : ViewBinding>(
    private val viewBinding: (parent: ViewGroup) -> VB,
    private val on: (item: Interface) -> Boolean,
    private val block: AdapterDelegateViewHolder<Item, VB>.() -> Unit,
) : AdapterDelegate<Interface> {
    override fun isForViewType(item: Interface): Boolean {
        return on(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): AdapterDelegateViewHolder<Item, VB> {
        val binding = viewBinding(parent)
        return AdapterDelegateViewHolder<Item, VB>(binding).also { holder -> block(holder) }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Interface) {
        (holder as AdapterDelegateViewHolder<Item, VB>)._item = item as Item
        holder._bind?.invoke()

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: Interface,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isNotEmpty()) {
            (holder as AdapterDelegateViewHolder<Item, VB>)._item = item as Item
            holder._bindForPayloads?.invoke(payloads)
        }
    }

}