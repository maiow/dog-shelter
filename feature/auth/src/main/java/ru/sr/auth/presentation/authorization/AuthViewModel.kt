package ru.sr.auth.presentation.authorization

import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import ru.sr.auth.domain.usecase.AuthTokenUseCase
import ru.sr.auth.domain.usecase.AuthUseCase
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