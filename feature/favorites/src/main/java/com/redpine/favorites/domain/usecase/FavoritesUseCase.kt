package com.redpine.favorites.domain.usecase

import com.redpine.core.domain.model.Dog

interface FavoritesUseCase {

    suspend fun getFavoriteDogs(): List<Dog>
    suspend fun isUserAuthorized(): Boolean
}