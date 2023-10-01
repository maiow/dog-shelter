package ru.sr.auth.domain.usecase.impl

import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.AuthenticationRepository

class ResetPasswordUseCaseImpl(
    private val repository: AuthenticationRepository
) : ResetPasswordUseCase {

    override suspend fun sendResetPasswordForEmail(email: String) {
        repository.resetPassword(email).await()
    }

}