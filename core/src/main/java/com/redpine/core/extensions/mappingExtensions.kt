package com.redpine.core.extensions

import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog

fun DogDto.toDog() = Dog(
    age = age,
    character = character,
    color = color,
    curatorPhone = curatorPhone,
    isMale = gender == "male",
    height = height,
    id = id,
    imageUrl = imageUrl,
    name = name,
    size = size,
    text = text,
    webLink = webLink,
    isFavorite = isFavorite
)

fun DogDto.toFavoriteDog() = Dog(
    age = age,
    character = character,
    color = color,
    curatorPhone = curatorPhone,
    isMale = gender == "male",
    height = height,
    id = id,
    imageUrl = imageUrl,
    name = name,
    size = size,
    text = text,
    webLink = webLink,
    isFavorite = true
)

fun List<DogDto>.toDogList(): List<Dog> = this.map { item -> item.toDog() }

fun List<DogDto>.toFavoriteDogList(): List<Dog> = this.map { item -> item.toFavoriteDog() }