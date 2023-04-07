package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.presentation.home.ItemDiffUtil

class HomeAdapter(onItemClick:(ClickableView, Item, Int) -> Unit) : ListDelegationAdapter<List<HomeScreen>>(
    verticalGridDelegate(onItemClick),
    horizontalGridDelegate(onItemClick),
)

class OneListItemAdapter(onItemClick:(ClickableView, Item, Int) -> Unit) : AsyncListDifferDelegationAdapter<Item>(ItemDiffUtil()) {

    init {
        delegatesManager
            .addDelegate(dogsDelegate(onItemClick))
            .addDelegate(newsDelegate())
    }

}
