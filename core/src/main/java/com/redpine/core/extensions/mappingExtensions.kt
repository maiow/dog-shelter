package com.redpine.core.extensions

import com.redpine.core.domain.model.Dog
import com.redpine.core.data.DogDto

fun DogDto.toDog() = Dog(
    age = age,
    character = character,
    color = color,
    curatorPhone = curatorPhone,
    gender = gender,
    height = height,
    id = id,
    imageUrl = imageUrl,
    name = name,
    size = size,
    text = text,
    webLink = webLink
)

fun List<DogDto>.toDogList(): List<Dog> = this.map { item -> item.toDog() }