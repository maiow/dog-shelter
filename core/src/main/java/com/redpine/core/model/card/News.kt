package com.redpine.core.model.card

class News(
    override val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String,
) : Item{
    override var isFavorite: Boolean = false
}
