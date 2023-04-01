package com.redpine.core.model.container

import com.redpine.core.model.card.Item

interface Container {
    val list: List<Item>
}