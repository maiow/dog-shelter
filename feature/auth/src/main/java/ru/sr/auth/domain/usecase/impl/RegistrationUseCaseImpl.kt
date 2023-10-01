package ru.sr.auth.domain.usecase.impl

import com.redpine.home.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.AuthenticationRepository

class RegistrationUseCaseImpl(private val repository: AuthenticationRepository) :
    RegistrationUseCase {

    override suspend fun createUser(email: String, password: String): String? {

        val newUser = repository.createUser(email, password).await().user

        newUser?.sendEmailVerification()?.await()

        return newUser?.email
    }
}