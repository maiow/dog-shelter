package com.redpine.profile.domain.usecase

interface LogoutUseCase {

    suspend fun logout(): Boolean

}