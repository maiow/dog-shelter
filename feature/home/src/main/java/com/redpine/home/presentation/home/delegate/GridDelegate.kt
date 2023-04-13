package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.core.tools.DOG_CONTAINER
import com.redpine.core.tools.NEWS_CONTAINER
import com.redpine.home.databinding.ItemContainerViewHolderBinding
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid

fun horizontalGridDelegate(
    onItemClick: (ClickableView, Item) -> Unit,
    onContainerAllButtonClick: (ClickableView) -> Unit) =
    adapterDelegateViewBinding<HorizontalGrid, HomeScreen, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        binding.btnAll.setOnClickListener {
            ClickableView.ALL_BUTTON.container = DOG_CONTAINER
            onContainerAllButtonClick(ClickableView.ALL_BUTTON)
        }
        bind {
            binding.bind(item){clickableView, item ->
                clickableView.listPosition = bindingAdapterPosition
                onItemClick(clickableView, item) }
        }
    }

fun verticalGridDelegate(
    onItemClick: (ClickableView, Item) -> Unit,
    onContainerAllButtonClick: (ClickableView) -> Unit
) =
    adapterDelegateViewBinding<VerticalGrid, HomeScreen, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        binding.btnAll.setOnClickListener {
            ClickableView.ALL_BUTTON.container = NEWS_CONTAINER
            onContainerAllButtonClick(ClickableView.ALL_BUTTON)
        }
        bind {
            binding.bind(item, onItemClick)
        }
    }


fun ItemContainerViewHolderBinding.bind(
    item: HomeScreen,
    onItemClick: (ClickableView, Item) -> Unit
) {
    val dogAdapter = OneListItemAdapter(onItemClick)
    recyclerView.adapter = dogAdapter
    dogAdapter.items = item.list
    itemTitle.text = itemTitle.context.getString(item.titleId)
    recyclerView.layoutManager = GridLayoutManager(
        recyclerView.context, item.spanCount, item.orientation, false
    )
}