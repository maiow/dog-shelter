package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.core.model.container.Container

class HomeAdapter: ListDelegationAdapter<List<Container>>(
    newDogContainerListDelegate(),
    recentSeenDogContainerListDelegate(),
    newsContainerListDelegate()
)