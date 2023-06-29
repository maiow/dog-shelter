package com.redpine.profile.domain.usecase.impl

import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.domain.usecase.ProfileInfoUseCase

class ProfileInfoUseCaseImpl(private val repository: ProfileRepository) : ProfileInfoUseCase {

    override suspend fun isUserAuthorized(): Boolean = repository.isUserAuthorized()

    override suspend fun getEmail(): String = repository.getEmail()

}