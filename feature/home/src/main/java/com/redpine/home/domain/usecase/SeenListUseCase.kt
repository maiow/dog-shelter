package com.redpine.home.domain.usecase

interface SeenListUseCase {

    suspend fun sendDogToSeenList(dogId: Int)

}