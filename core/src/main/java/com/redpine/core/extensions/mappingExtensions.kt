package com.redpine.core.extensions

import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog

fun DogDto.toDog() = Dog(
    age, character, color, curatorPhone, gender, height, id, imageUrl, name, size, text, webLink
)

fun DogDto.toFavoriteDog() = Dog(
    age, character, color, curatorPhone, gender, height, id, imageUrl, name, size, text, webLink,
    isFavorite = true
)

fun List<DogDto>.toDogList(): List<Dog> = this.map { item -> item.toDog() }

fun List<DogDto>.toFavoriteDogList(): List<Dog> = this.map { item -> item.toFavoriteDog() }