package com.redpine.core.model.card

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Dog(
    var age: String = "",
    var character: String = "",
    var color: String = "",
    var curator_phone: String = "",
    var gender: String = "",
    var height: Int = 20,
    override var id: Int = 0,
    var imageUrl: String = "",
    var name: String = "",
    var size: String = "",
    var text: String = "",
    @Exclude
    override var isFavorite: Boolean = false,
    @Exclude
    var isNew: Boolean = false,
) : Item
