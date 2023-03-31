package com.redpine.core.model

data class News(
    val id: Int,
    val title: String,
    val body: String,
//    val preview: String,
) : Item
