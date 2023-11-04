package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.DogInfoUseCase

class DogInfoUseCaseImpl(private val dogsRepository: DogsRepository) : DogInfoUseCase {

    override suspend fun getDogLikeInfo(dogId: Int): Boolean {
        return dogsRepository.getDogLikeInfo(dogId)
    }

    override suspend fun getDogImages(dogId: Int): List<String> {
        return dogsRepository.getDogImages(dogId)
    }
}