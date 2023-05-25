package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.FilterUseCase

class FilterUseCaseImpl(private val repository: DogsRepository) : FilterUseCase {
    override suspend fun filterDogs(
        minAge: String,
        maxAge: String,
        gender: String,
        size: String?,
        character: String
    ): List<Dog> {
        return repository.filterDogs(minAge, maxAge, gender, size, character)
    }
}