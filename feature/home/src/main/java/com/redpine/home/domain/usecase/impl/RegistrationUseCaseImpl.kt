package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.tasks.await

class RegistrationUseCaseImpl(private val repository: AuthenticationRepository):
    RegistrationUseCase {

    override suspend fun createUser(email: String, password: String): String? {

       return repository.createUser(email, password).await().user?.email
    }
}