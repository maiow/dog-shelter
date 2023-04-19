package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.presentation.home.GridDiffUtil
import com.redpine.home.presentation.home.ItemDiffUtil

class GridAdapter(
    onItemClick: (ClickableView, Item) -> Unit,
    onContainerAllButtonClick: (ClickableView) -> Unit
) :
    AsyncListDifferDelegationAdapter<Grid>(GridDiffUtil()) {

    init {
        delegatesManager
            .addDelegate(verticalGridDelegate(onItemClick, onContainerAllButtonClick))
            .addDelegate(horizontalGridDelegate(onItemClick, onContainerAllButtonClick))
    }
}

class ItemAdapter(onItemClick: (ClickableView, Item) -> Unit) :
    AsyncListDifferDelegationAdapter<Item>(ItemDiffUtil()) {

    init {
        delegatesManager
            .addDelegate(dogsDelegate(onItemClick))
            .addDelegate(newsDelegate(onItemClick))
    }
}
