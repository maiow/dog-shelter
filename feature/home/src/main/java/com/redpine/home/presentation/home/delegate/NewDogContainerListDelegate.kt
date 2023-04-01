package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.container.NewDogContainer
import com.redpine.core.model.container.Container
import com.redpine.home.R
import com.redpine.home.databinding.ItemContainerViewHolderBinding

fun newDogContainerListDelegate() =
    adapterDelegateViewBinding<NewDogContainer, Container, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            val dogAdapter = ListDelegationAdapter(dogListDelegate())
            binding.recyclerView.adapter = dogAdapter
            dogAdapter.items = item.list
            binding.itemTitle.text = context.getString(R.string.New)
            binding.recyclerView.layoutManager =
                GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
        }
    }