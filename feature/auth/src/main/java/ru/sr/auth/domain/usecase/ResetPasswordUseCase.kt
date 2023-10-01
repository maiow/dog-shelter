package ru.sr.auth.domain.usecase

interface ResetPasswordUseCase {

   suspend fun sendResetPasswordForEmail(email:String)
}