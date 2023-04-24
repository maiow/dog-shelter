package com.redpine.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class AdapterDelegateViewHolder<T, VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    internal var _item: T? = null
    val item get() = _item!!

    internal var _bind :(()->Unit)? =null
        private set

    internal var _bindForPayloads :((payloads: MutableList<Any>)->Unit)? =null
        private set


    fun bind(bindingBlock: () -> Unit) {
       this._bind = bindingBlock
    }

    fun bindForPayloads(bindingBlock: (payloads: MutableList<Any>) -> Unit) {
        _bindForPayloads = bindingBlock
    }
}