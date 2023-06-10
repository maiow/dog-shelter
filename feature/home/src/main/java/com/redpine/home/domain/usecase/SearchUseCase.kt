package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.Dog

interface SearchUseCase {

    suspend fun searchDogByName(query: String): Dog
}