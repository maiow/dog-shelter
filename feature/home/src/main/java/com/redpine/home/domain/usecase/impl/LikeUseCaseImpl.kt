package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.LikeUseCase
import javax.inject.Inject

class LikeUseCaseImpl @Inject constructor(
    private val dogsRepository: DogsRepository
) : LikeUseCase {

    override suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean {
        return dogsRepository.makeLikeDislike(dogId, isLike)
    }
}