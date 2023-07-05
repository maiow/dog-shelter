package com.redpine.home.presentation.authorization.auth

import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import com.redpine.home.domain.usecase.AuthTokenUseCase
import com.redpine.home.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val tokenProvider: AuthTokenUseCase,
) : BaseViewModel() {

    fun startAuth(email: String, password: String) = scopeLaunch {
            val token = authUseCase.authEmail(email, password).toString()
            tokenProvider.putToken(token)
        }

    fun validation(email: String) {
        _loadState.value = if (email.emailValidation()) LoadState.ENABLE_BUTTON
        else LoadState.START
    }
}