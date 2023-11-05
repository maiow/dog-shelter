package com.redpine.auth.domain.usecase.impl

import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.tasks.await

class ResetPasswordUseCaseImpl(
    private val repository: AuthenticationEmailAndPasswordRepository
) : ResetPasswordUseCase {

    override suspend fun sendResetPasswordForEmail(email: String) {
        repository.resetPassword(email).await()
    }

}