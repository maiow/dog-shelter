package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.Dog

interface FilterUseCase {

    suspend fun filterDogs(
        minAge: String,
        maxAge: String,
        gender: String,
        size: String?,
        character: String
    ): List<Dog>
}