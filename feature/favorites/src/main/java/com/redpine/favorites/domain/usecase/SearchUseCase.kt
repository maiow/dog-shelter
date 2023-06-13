package com.redpine.favorites.domain.usecase

import com.redpine.core.domain.model.Dog

interface SearchUseCase {

    suspend fun searchDogByName(query: String): Dog
}