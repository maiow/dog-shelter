package com.redpine.auth.domain.usecase

interface AuthEmailAndPasswordUseCase {

    suspend fun authEmail(email: String, password: String, isVk: Boolean): String?
}