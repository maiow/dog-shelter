package com.redpine.auth.domain.usecase

interface AuthTokenUseCase {

    fun putToken(token: String)
}