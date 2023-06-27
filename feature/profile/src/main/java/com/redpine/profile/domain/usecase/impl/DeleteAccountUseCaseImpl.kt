package com.redpine.profile.domain.usecase.impl

import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.domain.usecase.DeleteAccountUseCase

class DeleteAccountUseCaseImpl(
    private val repository: ProfileRepository
) : DeleteAccountUseCase {

    override suspend fun deleteAccount(): Boolean = repository.deleteAccount()

    override suspend fun reauthenticateUser(password: String): Boolean =
        repository.reauthenticateUser(password)
}