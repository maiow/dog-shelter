package com.redpine.home.domain.model.grid

import com.redpine.core.domain.model.Item

interface Grid {
    val titleId: Int
    var list: List<Item>
    val orientation: Int
    val spanCount: Int
}