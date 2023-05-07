package com.redpine.home.presentation.home.adapter.delegate

import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.redpine.adapter.adapterDelegate
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.databinding.ItemContainerViewHolderBinding
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

fun horizontalGridDelegate(
    onItemClick: (ClickableView, Item?) -> Unit,
) = adapterDelegate<Grid, HorizontalGrid, ItemContainerViewHolderBinding>({ parent ->
    ItemContainerViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
}) {
    val itemAdapter = ItemAdapter { clickableView, item ->
        clickableView.listPosition = bindingAdapterPosition
        onItemClick(clickableView, item)
    }
    binding.recyclerView.adapter = itemAdapter
    binding.btnAll.setOnClickListener { onItemClick(ClickableView.DOG_ALL_BUTTON, null) }
    bind {
        binding.bind(item, itemAdapter)
    }
    bindForPayloads {
        itemAdapter.submitList(item.list)
    }
}

fun verticalGridDelegate(
    onItemClick: (ClickableView, Item?) -> Unit,
) = adapterDelegate<Grid, VerticalGrid, ItemContainerViewHolderBinding>({ parent ->
    ItemContainerViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
}) {
    val itemAdapter = ItemAdapter(onItemClick)
    binding.recyclerView.adapter = itemAdapter
    binding.btnAll.setOnClickListener {
        onItemClick(ClickableView.NEWS_ALL_BUTTON, null)
    }
    bind {
        binding.bind(item, itemAdapter)
    }
}

fun ItemContainerViewHolderBinding.bind(
    grid: Grid, adapter: ItemAdapter,
) {
    adapter.submitList(grid.list)
    itemTitle.text = itemTitle.context.getString(grid.titleId)
    recyclerView.layoutManager =
        GridLayoutManager(
            recyclerView.context, grid.spanCount, grid.orientation, false
        )
}