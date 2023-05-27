package com.redpine.favorites.domain.usecase

interface DislikeUseCase {

    suspend fun makeDislike(dogId: Int): Boolean

}