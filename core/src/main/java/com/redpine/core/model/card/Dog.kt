package com.redpine.core.model.card

interface Dog: Item {
    val id: Int
    val name: String
    val age: String
    //    val gender: String,
//    val photo: String,
    val isFavorite: Boolean
    val isNew: Boolean
}

