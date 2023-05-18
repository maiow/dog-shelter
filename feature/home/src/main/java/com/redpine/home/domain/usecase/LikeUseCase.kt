package com.redpine.home.domain.usecase

interface LikeUseCase {

    suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean

}