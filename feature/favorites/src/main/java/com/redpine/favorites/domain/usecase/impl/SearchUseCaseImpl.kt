package com.redpine.favorites.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.SearchUseCase

class SearchUseCaseImpl(
    private val repository: FavoritesRepository
) : SearchUseCase {

    override suspend fun searchDogByName(query: String): Dog =
        repository.searchDogByName(query)
}