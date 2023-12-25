package com.redpine.home.presentation.authorization.resetpassword

import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.presentation.authorization.state.TypeAuthListener
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
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