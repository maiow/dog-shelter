package com.redpine.home.presentation.authorization.resetpassword

import androidx.lifecycle.ViewModel
import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.presentation.authorization.state.TypeAuthListener

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : BaseViewModel() {

    fun resetPassword(email: String) {
        scopeAction {
            resetPasswordUseCase.sendResetPasswordForEmail(email)
        }
    }

    fun validation(text: String, type: TypeAuthListener) =
        when (type) {
            TypeAuthListener.PASSWORD -> throw RuntimeException("no Password Type")
            TypeAuthListener.EMAIL -> {
                _loadState.value =
                    if (text.emailValidation()) LoadState.ENABLE_BUTTON else LoadState.START
            }
        }

}