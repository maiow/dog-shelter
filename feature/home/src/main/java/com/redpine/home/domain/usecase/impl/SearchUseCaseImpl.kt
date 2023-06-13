package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.SearchUseCase

class SearchUseCaseImpl (
    private val dogsRepository: DogsRepository
) : SearchUseCase {

    override suspend fun searchDogByName(query: String): Dog {
        return dogsRepository.searchDogByName(query)
    }
}