package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.Dog
import com.redpine.core.model.Item
import com.redpine.core.model.ItemContainer
import com.redpine.core.model.News
import com.redpine.home.databinding.ItemContainerViewHolderBinding

fun containerListDelegate() =
    adapterDelegateViewBinding<ItemContainer, Item, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)

    }) {
        bind {
            val homeAdapter = HomeAdapter()
            binding.recyclerView.adapter = homeAdapter
            homeAdapter.items = item.list
            when(item.list.first())  {
                is Dog  -> {
                    if((item.list.first() as Dog).isRecentSeen) {
                        binding.recyclerView.layoutManager =
                            GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
                    } else binding.recyclerView.layoutManager =
                        GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
                }
                is News -> binding.recyclerView.layoutManager =
                    LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
            }
        }
    }