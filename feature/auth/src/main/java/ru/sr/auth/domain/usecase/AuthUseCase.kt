package ru.sr.auth.domain.usecase

interface AuthUseCase {

   suspend fun authEmail(email: String, password: String): String?
}