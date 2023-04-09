package com.redpine.home.domain.usecase

interface AuthUseCase {

   suspend fun authEmail(email: String, password: String): String?
}