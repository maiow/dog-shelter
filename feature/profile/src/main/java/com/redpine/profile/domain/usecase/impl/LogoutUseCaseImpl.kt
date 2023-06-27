package com.redpine.profile.domain.usecase.impl

import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.domain.usecase.LogoutUseCase

class LogoutUseCaseImpl(private val repository: ProfileRepository) : LogoutUseCase {

    override suspend fun logout(): Boolean = repository.logout()
}