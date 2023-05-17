package com.redpine.core.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class DogDto(
    var age: String = "",
    var character: String = "",
    var color: String = "",
    var curatorPhone: String = "",
    var gender: String = "",
    var height: String = "20",
    var id: Int = 0,
    var imageUrl: String = "",
    var name: String = "",
    var size: String = "",
    var text: String = "",
    var webLink: String = "",
    @Exclude
    var isFavorite: Boolean = false,
    @Exclude
    var isNew: Boolean = false
)
