package com.redpine.home.domain.usecase

interface SeenListUseCase {

    suspend fun sendDogToSeenList(id: Int)

}