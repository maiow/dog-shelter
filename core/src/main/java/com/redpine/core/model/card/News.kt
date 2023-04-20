package com.redpine.core.model.card

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class News(
    var body: String = "",
    override var id: Int = 0,
    var imageUrl: String = "",
    @Exclude
    override var isFavorite: Boolean = false,
    var title: String = "",
) : Item
