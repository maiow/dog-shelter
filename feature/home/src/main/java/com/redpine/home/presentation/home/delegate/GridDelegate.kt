package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.home.databinding.ItemContainerViewHolderBinding
import com.redpine.home.presentation.home.HomeScreen
import com.redpine.home.presentation.home.HorizontalGrid
import com.redpine.home.presentation.home.VerticalGrid

fun horizontalGridDelegate() =
    adapterDelegateViewBinding<HorizontalGrid, HomeScreen, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.bind(item)
        }
    }

fun verticalGridDelegate() =
    adapterDelegateViewBinding<VerticalGrid, HomeScreen, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.bind(item)
        }
    }


fun ItemContainerViewHolderBinding.bind(
    item: HomeScreen,
) {
    val dogAdapter = OneListItemAdapter()
    recyclerView.adapter = dogAdapter
    dogAdapter.items = item.list
    itemTitle.text = itemTitle.context.getString(item.titleId)
    recyclerView.layoutManager = GridLayoutManager(
        recyclerView.context, item.spanCount, item.orientation, false
    )
}