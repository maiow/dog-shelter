package com.redpine.home.presentation.home.delegate

import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.core.tools.DOG_CONTAINER
import com.redpine.core.tools.NEWS_CONTAINER
import com.redpine.home.databinding.ItemContainerViewHolderBinding
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid

fun horizontalGridDelegate(
    onItemClick: (ClickableView, Item) -> Unit,
    onContainerAllButtonClick: (ClickableView) -> Unit) =
    adapterDelegateViewBinding<HorizontalGrid, Grid, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        val itemAdapter = ItemAdapter{ clickableView, item ->
            clickableView.listPosition = bindingAdapterPosition
            onItemClick(clickableView, item)
        }
        Log.e(TAG, "${itemAdapter.hashCode()}")
        binding.recyclerView.adapter = itemAdapter
        binding.btnAll.setOnClickListener {
            ClickableView.ALL_BUTTON.container = DOG_CONTAINER
            onContainerAllButtonClick(ClickableView.ALL_BUTTON)
        }
        bind {
                Log.d(TAG, "payloadsList: $it")
                binding.bind(item, itemAdapter)
        }
    }

fun verticalGridDelegate(
    onItemClick: (ClickableView, Item) -> Unit,
    onContainerAllButtonClick: (ClickableView) -> Unit
) =
    adapterDelegateViewBinding<VerticalGrid, Grid, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        val itemAdapter = ItemAdapter(onItemClick)
        binding.recyclerView.adapter = itemAdapter
        binding.btnAll.setOnClickListener {
            ClickableView.ALL_BUTTON.container = NEWS_CONTAINER
            onContainerAllButtonClick(ClickableView.ALL_BUTTON)
        }
        bind {
            Log.d(TAG, "verticalGridDelegate: $it")
            binding.bind(item, itemAdapter)
        }
    }


fun ItemContainerViewHolderBinding.bind(
    item: Grid,
    adapter: ItemAdapter
) {
    adapter.items = item.list
    Log.d(TAG, "GridDelegate:\n${adapter.items.joinToString("\n")}")
    itemTitle.text = itemTitle.context.getString(item.titleId)
    recyclerView.layoutManager = GridLayoutManager(
        recyclerView.context, item.spanCount, item.orientation, false
    )
}