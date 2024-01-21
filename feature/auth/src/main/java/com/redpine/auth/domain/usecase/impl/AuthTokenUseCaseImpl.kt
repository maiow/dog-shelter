package com.redpine.auth.domain.usecase.impl

import com.redpine.auth.domain.usecase.AuthTokenUseCase
import com.redpine.core.domain.TokenProvider

class AuthTokenUseCaseImpl(
    private val tokenProvider: TokenProvider
) : AuthTokenUseCase {

    override fun putToken(token: String) {
        tokenProvider.putToken(token)
    }
}