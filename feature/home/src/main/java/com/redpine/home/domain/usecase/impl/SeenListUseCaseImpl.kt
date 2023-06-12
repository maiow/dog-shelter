package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.SeenListUseCase

class SeenListUseCaseImpl(private val dogsRepository: DogsRepository): SeenListUseCase {

    override suspend fun sendDogToSeenList(dogId: Int) {
        dogsRepository.sendDogToSeenList(dogId)
    }
}