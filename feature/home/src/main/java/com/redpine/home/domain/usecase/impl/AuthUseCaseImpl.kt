package com.redpine.home.domain.usecase.impl

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.AuthUseCase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : AuthUseCase {

    override suspend fun authEmail(email: String, password: String): String {

        val user = authRepository.authEmail(email, password).await().user

        return if (user?.isEmailVerified == true) user.uid
        else throw FirebaseAuthInvalidCredentialsException("403", "noEmailVerifications")

    }
}