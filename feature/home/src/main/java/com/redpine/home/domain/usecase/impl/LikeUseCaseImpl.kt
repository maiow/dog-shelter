package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.LikeUseCase

class LikeUseCaseImpl (
    private val dogsRepository: DogsRepository
) : LikeUseCase {

    override suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean {
        return dogsRepository.makeLikeDislike(dogId, isLike)
    }
}