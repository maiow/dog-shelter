package ru.sr.auth.domain.usecase

interface RegistrationUseCase{

   suspend fun createUser(email: String, password: String): String?
}