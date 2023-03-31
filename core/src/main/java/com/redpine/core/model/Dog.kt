package com.redpine.core.model

data class Dog(
    val id: Int,
    val name: String,
    val age: String,
//    val gender: String,
//    val photo: String,
    val isFavorite: Boolean,
    val isRecentSeen: Boolean,
) : Item
