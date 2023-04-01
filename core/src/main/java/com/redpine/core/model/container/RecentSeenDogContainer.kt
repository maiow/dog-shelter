package com.redpine.core.model.container

import com.redpine.core.model.card.RecentSeenDog

data class RecentSeenDogContainer(
//    val title: String,
    override val list: List<RecentSeenDog>,
): Container