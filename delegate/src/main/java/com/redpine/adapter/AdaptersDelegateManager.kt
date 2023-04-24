package com.redpine.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdaptersDelegateManager<I>() {

    private val mapDelegates = mutableMapOf<Int, AdapterDelegate<I>>()
    val test: SparseArray<AdapterDelegate<I>> = SparseArray()

    fun addDelegate(delegate: AdapterDelegate<I>) {
        var viewType = mapDelegates.size
        if (mapDelegates[viewType] == null) mapDelegates[viewType] = delegate
        else {
            var isAdd = false
            while (!isAdd) {
                viewType++
                if (mapDelegates[viewType] == null) {
                    mapDelegates[viewType] = delegate
                    isAdd = true
                }
            }
        }
    }

    fun getItemViewType(items: I): Int {
        val delegatesCounter = mapDelegates.size
        for (index in 0 until delegatesCounter) {
            val listDelegate = mapDelegates.toList()
            if (listDelegate[index].second.isForViewType(items,)
            ) return listDelegate[index].first
        }
        return throw Exception("AdapterDelegate not added")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate = mapDelegates[viewType] ?: throw Exception("AdapterDelegate not added")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(item: I, holder: ViewHolder) {
        val delegate =
            mapDelegates[holder.itemViewType] ?: throw Exception("AdapterDelegate not added")
        delegate.onBindViewHolder(holder, item)
    }

    fun onBindViewHolder(item: I, holder: ViewHolder,payloads:MutableList<Any>) {
        val delegate =
            mapDelegates[holder.itemViewType] ?: throw Exception("AdapterDelegate not added")
        delegate.onBindViewHolder(holder, item,payloads)
    }
}