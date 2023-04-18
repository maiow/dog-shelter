package com.redpine.home.domain.usecase

interface RegistrationUseCase{

   suspend fun createUser(email: String, password: String): String?
}