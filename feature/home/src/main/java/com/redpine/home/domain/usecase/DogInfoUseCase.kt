package com.redpine.home.domain.usecase

interface DogInfoUseCase {

    suspend fun getDogLikeInfo(dogId: Int): Boolean

    suspend fun getDogImages(dogId: Int): List<String>
}