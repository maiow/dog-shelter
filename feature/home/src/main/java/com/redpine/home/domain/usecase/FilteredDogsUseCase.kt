package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.Dog

interface FilteredDogsUseCase {

    suspend fun getFilteredDogs(): List<Dog>
}