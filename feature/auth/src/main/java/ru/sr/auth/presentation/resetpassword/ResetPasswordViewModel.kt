package com.redpine.home.presentation.authorization.resetpassword

import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import ru.sr.auth.presentation.state.TypeAuthListener

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : BaseViewModel() {

    fun resetPassword(email: String) =
        scopeLaunch {
            resetPasswordUseCase.sendResetPasswordForEmail(email)
        }

    fun validation(text: String, type: TypeAuthListener) =
        when (type) {
            TypeAuthListener.PASSWORD -> throw RuntimeException("no Password Type")
            TypeAuthListener.EMAIL ->
                _loadState.value =
                    if (text.emailValidation()) LoadState.ENABLE_BUTTON else LoadState.START
        }
}