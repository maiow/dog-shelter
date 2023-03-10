package com.redpine.favorites.domain.model

class FavoriteDogs(
    val resImgId: Int,
    val name: String,
    val sex: String,
    val height: Int,
    val age: Float //Float will be needed for 1.5 years or puppies <1 year old
)