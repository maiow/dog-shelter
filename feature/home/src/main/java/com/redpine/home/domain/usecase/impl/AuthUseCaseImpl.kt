package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await

class AuthUseCaseImpl(private val authRepository: AuthenticationRepository) : AuthUseCase {

    override suspend fun authEmail(email: String, password: String): String? {

        val token = authRepository.authEmail(email, password).await().user?.uid

        return if (authRepository.userEmailVerified()) token
        else null

    }
}