package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.tasks.await

class ResetPasswordUseCaseImpl(
    private val repository: AuthenticationRepository
) : ResetPasswordUseCase {

    override suspend fun sendResetPasswordForEmail(email: String) {
        repository.resetPassword(email).await()
    }

}