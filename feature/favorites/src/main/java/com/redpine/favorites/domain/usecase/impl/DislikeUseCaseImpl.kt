package com.redpine.favorites.domain.usecase.impl

import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.DislikeUseCase

class DislikeUseCaseImpl(
    private val repository: FavoritesRepository
) : DislikeUseCase {

    override suspend fun makeDislike(dogId: Int): Boolean {
        return repository.makeDislike(dogId)
    }
}