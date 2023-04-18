package com.redpine.core.model.card

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Dog(
    override val id: Int = 0,
    val name: String = "",
    val age: String = "",
//    val character: String ="",
//    val color: String ="",
//    val curator_phone: String ="",
//    val gallery: List<String> = emptyList<>,
//    val gender: String = "",
//    val height: Int = 20,
//    val size: String ="",
//    val text: String ="",
    val testText: String = "",
    @Exclude
    override var isFavorite: Boolean = false,
    val isNew: Boolean = false,
) : Item
