package com.redpine.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface AdapterDelegate<I> {

    fun isForViewType(item: I): Boolean

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: I)

    fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: I,
        payloads: MutableList<Any>,
    )
}

inline fun <T,reified I : T ,VB:ViewBinding> adapterDelegate(
    noinline viewBinding: (parent: ViewGroup) -> VB,
    noinline on: (item: T) -> Boolean = { item -> item is I },
    noinline block: AdapterDelegateViewHolder<I, VB>.()->Unit,
): AdapterDelegate<T> {

    return DslListAdapterDelegate(viewBinding, on, block)
}

