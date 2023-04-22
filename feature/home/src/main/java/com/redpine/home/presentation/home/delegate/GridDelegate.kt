package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.databinding.ItemContainerViewHolderBinding
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid
import com.redpine.home.presentation.home.GridDiffUtil

fun horizontalGridDelegate(
    onItemClick: (ClickableView, Item?) -> Unit
): AdapterDelegate<List<Grid>> {
    return adapterDelegateViewBinding<HorizontalGrid, Grid, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        val itemAdapter = ItemAdapter { clickableView, item ->
            clickableView.listPosition = bindingAdapterPosition
            onItemClick(clickableView, item)
        }
        binding.recyclerView.adapter = itemAdapter
        binding.btnAll.setOnClickListener { onItemClick(ClickableView.DOG_ALL_BUTTON,null) }
        bind { binding.bind(item, itemAdapter, it) }
    }
}

fun verticalGridDelegate(
    onItemClick: (ClickableView, Item?) -> Unit
): AdapterDelegate<List<Grid>> {
    return adapterDelegateViewBinding<VerticalGrid, Grid, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        val itemAdapter = ItemAdapter(onItemClick)
        binding.recyclerView.adapter = itemAdapter
        binding.btnAll.setOnClickListener {
            onItemClick(ClickableView.NEWS_ALL_BUTTON,null)
        }
        bind { binding.bind(item, itemAdapter, it) }
    }
}

fun ItemContainerViewHolderBinding.bind(
    grid: Grid, adapter: ItemAdapter, payloads: List<Any>
) {
    adapter.items = grid.list
    itemTitle.text = itemTitle.context.getString(grid.titleId)
    if (!payloads.contains(GridDiffUtil.LIST_UPDATE)) recyclerView.layoutManager =
        GridLayoutManager(
            recyclerView.context, grid.spanCount, grid.orientation, false
        )
}