package com.redpine.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val age: String,
    val character: String,
    val color: String,
    val curatorPhone: String,
    val isMale: Boolean,
    val height: String,
    override val id: Int,
    val imageUrl: String,
    val name: String,
    val size: String,
    val text: String,
    val webLink: String,
    override var isFavorite: Boolean = false,
    var isNew: Boolean = false,
) : Item, Parcelable