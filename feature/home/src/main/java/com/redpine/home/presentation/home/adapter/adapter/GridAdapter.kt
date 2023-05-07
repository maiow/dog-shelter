package com.redpine.home.presentation.home.adapter.delegate

import com.redpine.adapter.ListDelegateAdapter
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.presentation.home.adapter.diffutil.GridDiffUtil
import com.redpine.home.presentation.home.adapter.diffutil.ItemDiffUtil

class GridAdapter(
    onItemClick: (ClickableView, Item?) -> Unit,
) : ListDelegateAdapter<Grid>(GridDiffUtil()) {

    init {
        addDelegate(verticalGridDelegate(onItemClick))
        addDelegate(horizontalGridDelegate(onItemClick))
    }
}

class ItemAdapter(
    onItemClick: (ClickableView, Item) -> Unit
) : ListDelegateAdapter<Item>(ItemDiffUtil()) {

    init {
        addDelegate(dogsDelegate(onItemClick))
        addDelegate(newsDelegate(onItemClick))

    }
}
