package com.redpine.home.domain.model.homeScreen

import com.redpine.core.model.card.Item

interface HomeScreen {
    val titleId: Int
    val list: List<Item>
    val orientation: Int
    val spanCount: Int
}