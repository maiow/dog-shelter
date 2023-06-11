package com.redpine.favorites.domain

import com.redpine.core.domain.model.Dog

interface FavoritesRepository {

    suspend fun getFavoriteDogs(): List<Dog>

    suspend fun makeDislike(dogId: Int): Boolean

    suspend fun searchDogByName(query: String): Dog
}