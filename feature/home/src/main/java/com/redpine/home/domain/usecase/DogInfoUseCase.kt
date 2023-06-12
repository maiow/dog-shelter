package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.Dog

interface DogInfoUseCase {

    suspend fun getDogInfo(dogId: Int): Dog

    suspend fun getDogImages(dogId: Int): List<String>
}