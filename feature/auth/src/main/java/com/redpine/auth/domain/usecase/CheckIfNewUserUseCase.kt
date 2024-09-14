package com.redpine.auth.domain.usecase

interface CheckIfNewUserUseCase {

    suspend fun isNewUser(email: String): Boolean

}
