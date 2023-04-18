package com.redpine.home.presentation.authorization.auth

import android.util.Log
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.TokenProvider
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.presentation.authorization.state.TypeAuthListener
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val tokenProvider: TokenProvider,
) : BaseViewModel() {

    private var isEmailValidation = false
    private var isPasswordValidation = false

    fun startAuth(email: String, password: String) =
        scopeAction {
            val token = authUseCase.authEmail(email, password).toString()
            tokenProvider.putToken(token)
            Log.e("kart", "$token = ${tokenProvider.getToken()}")
        }

    fun validation(text: String, type: TypeAuthListener) {
        when (type) {
            TypeAuthListener.PASSWORD -> passwordValidation(text)
            TypeAuthListener.EMAIL -> emailValidation(text)
        }

        _loadState.value = if (isEmailValidation && isPasswordValidation) LoadState.ENABLE_BUTTON
        else LoadState.START

    }

    private fun emailValidation(email: String) {
        isEmailValidation = email.emailValidation()
    }

    private fun passwordValidation(password: String) {
        isPasswordValidation = password.length >= 8
    }
}