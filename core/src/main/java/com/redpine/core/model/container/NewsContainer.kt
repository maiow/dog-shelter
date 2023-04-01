package com.redpine.core.model.container

import com.redpine.core.model.card.News

data class NewsContainer(
//    val title: String,
    override val list: List<News>,
): Container