package com.redpine.core.model.card

data class News(
    override val id: Int,
    val title: String,
    val body: String,
//    val preview: String,
) : Item{
    override var isFavorite: Boolean = false
}
