package ru.sr.auth.domain.usecase.impl

import com.redpine.core.domain.TokenProvider
import ru.sr.auth.domain.usecase.AuthTokenUseCase

class AuthTokenUseCaseImpl(
    private val tokenProvider: TokenProvider
) : AuthTokenUseCase {

    override fun putToken(token: String) {
        tokenProvider.putToken(token)
    }
}