package com.redpine.auth.domain.usecase.impl

import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.usecase.CheckIfNewUserUseCase

class CheckIfNewUserUseCaseImpl(
    private val repository: AuthenticationEmailAndPasswordRepository
) : CheckIfNewUserUseCase {

    override suspend fun isNewUser(email: String): Boolean {
        return repository.checkIfNewUser(email)
    }
}
