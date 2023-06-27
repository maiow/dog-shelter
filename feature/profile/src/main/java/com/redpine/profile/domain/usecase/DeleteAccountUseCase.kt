package com.redpine.profile.domain.usecase

interface DeleteAccountUseCase {

    suspend fun reauthenticateUser(password: String): Boolean
    suspend fun deleteAccount(): Boolean

}