package com.redpine.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.auth.domain.AuthGoogleRepository
import com.redpine.auth.domain.usecase.AuthEmailAndPasswordUseCase
import com.redpine.auth.domain.usecase.AuthTokenUseCase
import com.redpine.auth.domain.usecase.RegistrationUseCase
import com.redpine.auth.domain.usecase.ResetPasswordUseCase
import com.redpine.auth.presentation.auth.AuthViewModel
import com.redpine.auth.presentation.registration.RegistrationViewModel
import com.redpine.auth.presentation.resetpassword.ResetPasswordViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val authTokenUseCase: AuthTokenUseCase,
    private val authUseCase: AuthEmailAndPasswordUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val authGoogleRepository: AuthGoogleRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        AuthViewModel::class.java -> AuthViewModel(
            authUseCase,
            authTokenUseCase,
            authGoogleRepository
        ) as T

        RegistrationViewModel::class.java -> RegistrationViewModel(registrationUseCase) as T
        ResetPasswordViewModel::class.java -> ResetPasswordViewModel(resetPasswordUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }
}