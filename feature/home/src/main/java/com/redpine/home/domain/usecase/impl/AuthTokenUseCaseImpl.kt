package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.TokenProvider
import com.redpine.home.domain.usecase.AuthTokenUseCase

class AuthTokenUseCaseImpl(
    private val tokenProvider: TokenProvider
) : AuthTokenUseCase {

    override fun putToken(token: String) {
        tokenProvider.putToken(token)
    }
}