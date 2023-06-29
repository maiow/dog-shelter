package com.redpine.profile.domain.usecase

interface ProfileInfoUseCase {

    suspend fun isUserAuthorized(): Boolean
    suspend fun getEmail(): String
}