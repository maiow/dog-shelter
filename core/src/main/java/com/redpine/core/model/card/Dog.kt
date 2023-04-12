package com.redpine.core.model.card

data class Dog(
    override val id: Int,
    val name: String,
    val age: String,
    val testText:String,
    override var isFavorite: Boolean,
    val isNew: Boolean,
) : Item
