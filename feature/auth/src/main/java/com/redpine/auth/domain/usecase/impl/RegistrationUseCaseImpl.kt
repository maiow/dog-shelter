package com.redpine.auth.domain.usecase.impl

import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.tasks.await

class RegistrationUseCaseImpl(private val repository: AuthenticationEmailAndPasswordRepository) :
    RegistrationUseCase {

    override suspend fun createUser(email: String, password: String): String? {

        val newUser = repository.createUser(email, password).await().user

        newUser?.sendEmailVerification()?.await()

        return newUser?.email
    }
}