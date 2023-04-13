package com.redpine.core.tools

enum class ClickableView(var itemPosition: Int = -1, var listPosition: Int = -1, var container: String = "empty") {
    FAVORITE,
    DOG,
    NEWS,
    ALL_BUTTON
}

const val DOG_CONTAINER = "dog_container"
const val NEWS_CONTAINER = "news_container"