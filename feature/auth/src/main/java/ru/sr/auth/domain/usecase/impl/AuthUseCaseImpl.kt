package ru.sr.auth.domain.usecase.impl

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import ru.sr.auth.domain.usecase.AuthUseCase
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.AuthenticationRepository

class AuthUseCaseImpl(private val authRepository: AuthenticationRepository) : AuthUseCase {

    override suspend fun authEmail(email: String, password: String): String {

        val user = authRepository.authEmail(email, password).await().user

        return if (user?.isEmailVerified == true) user.uid
        else throw FirebaseAuthInvalidCredentialsException("403", "noEmailVerifications")

    }
}