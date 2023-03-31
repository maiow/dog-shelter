package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.Item

class HomeAdapter: ListDelegationAdapter<List<Item>>(
    dogListDelegate(),
    newsListDelegate(),
)