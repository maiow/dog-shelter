package com.redpine.favorites.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.FavoritesUseCase

class FavoritesUseCaseImpl(private val repository: FavoritesRepository) : FavoritesUseCase {

    override suspend fun getFavoriteDogs(): List<Dog> = repository.getFavoriteDogs()

    override suspend fun isUserAuthorized(): Boolean = repository.isUserAuthorized()
}