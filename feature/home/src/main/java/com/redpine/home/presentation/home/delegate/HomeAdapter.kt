package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.home.presentation.home.HomeScreen
import com.redpine.home.presentation.home.ItemDiffUtil

class HomeAdapter : ListDelegationAdapter<List<HomeScreen>>(
    verticalGridDelegate(),
    horizontalGridDelegate(),
)

class OneListItemAdapter : AsyncListDifferDelegationAdapter<Item>(ItemDiffUtil()) {

    init {
        delegatesManager.addDelegate(dogsDelegate())
            .addDelegate(newsDelegate())
    }

}
