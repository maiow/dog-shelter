package com.redpine.favorites.presentation.adapter

import com.redpine.adapter.ListDelegateAdapter
import com.redpine.core.domain.model.Item
import com.redpine.core.tools.ClickableView

class FavoritesAdapter(
    onItemClick: (ClickableView, Item) -> Unit
) : ListDelegateAdapter<Item>(ItemDiffUtil()) {

    init {
        addDelegate(dogsDelegate(onItemClick))
    }
}
