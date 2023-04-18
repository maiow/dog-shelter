package com.redpine.home.domain.usecase

interface ResetPasswordUseCase {

   suspend fun sendResetPasswordForEmail(email:String)
}