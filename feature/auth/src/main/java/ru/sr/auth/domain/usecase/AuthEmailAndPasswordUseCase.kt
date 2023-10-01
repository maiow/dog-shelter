package ru.sr.auth.domain.usecase

interface AuthEmailAndPasswordUseCase {

   suspend fun authEmail(email: String, password: String): String?
}