package com.redpine.auth.domain.usecase

interface ResetPasswordUseCase {

    suspend fun sendResetPasswordForEmail(email: String)
}