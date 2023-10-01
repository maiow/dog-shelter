package ru.sr.auth.domain.usecase.impl

import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.AuthenticationEmailAndPasswordRepository

class ResetPasswordUseCaseImpl(
    private val repository: AuthenticationEmailAndPasswordRepository
) : ResetPasswordUseCase {

    override suspend fun sendResetPasswordForEmail(email: String) {
        repository.resetPassword(email).await()
    }

}