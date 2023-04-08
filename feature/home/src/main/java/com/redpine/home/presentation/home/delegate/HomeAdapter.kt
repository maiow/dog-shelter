package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.home.presentation.home.HomeScreen
import com.redpine.home.presentation.home.ItemDiffUtil
import com.redpine.home.presentation.tools.ClickableView
import com.redpine.home.presentation.tools.Query

class HomeAdapter : ListDelegationAdapter<List<HomeScreen>>(
    verticalGridDelegate(),
    horizontalGridDelegate(),
)

class OneListItemAdapter : AsyncListDifferDelegationAdapter<Item>(ItemDiffUtil()) {

    init {
        delegatesManager.addDelegate(dogsDelegate { query: Query, view: ClickableView -> Unit })
            .addDelegate(newsDelegate())
    }

}
