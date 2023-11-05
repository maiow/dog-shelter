package com.redpine.auth.domain.usecase

interface RegistrationUseCase {

    suspend fun createUser(email: String, password: String): String?
}