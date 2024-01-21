package com.redpine.auth.domain.usecase.impl

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.usecase.AuthEmailAndPasswordUseCase
import kotlinx.coroutines.tasks.await

class AuthEmailAndPasswordUseCaseImpl(private val authRepository: AuthenticationEmailAndPasswordRepository) :
    AuthEmailAndPasswordUseCase {

    override suspend fun authEmail(email: String, password: String): String {

        val user = authRepository.authEmail(email, password).await().user

        return if (user?.isEmailVerified == true) user.uid
        else throw FirebaseAuthInvalidCredentialsException("403", "noEmailVerifications")

    }
}