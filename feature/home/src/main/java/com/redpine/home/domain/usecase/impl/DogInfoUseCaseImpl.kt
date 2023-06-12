package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.DogInfoUseCase

class DogInfoUseCaseImpl(private val dogsRepository: DogsRepository): DogInfoUseCase {

    override suspend fun getDogInfo(dogId: Int): Dog {
        return dogsRepository.getDogInfo(dogId)
    }

    override suspend fun getDogImages(dogId: Int): List<String> {
        return dogsRepository.getDogImages(dogId)
    }
}