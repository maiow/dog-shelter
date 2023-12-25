package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegistrationUseCaseImpl @Inject constructor(
    private val repository: AuthenticationRepository
) : RegistrationUseCase {

    override suspend fun createUser(email: String, password: String): String? {

        val newUser = repository.createUser(email, password).await().user

        newUser?.sendEmailVerification()?.await()

        return newUser?.email
    }
}