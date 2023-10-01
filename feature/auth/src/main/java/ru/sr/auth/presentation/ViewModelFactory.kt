package ru.sr.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sr.auth.domain.usecase.AuthTokenUseCase
import ru.sr.auth.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordViewModel
import ru.sr.auth.presentation.authorization.AuthViewModel
import ru.sr.auth.presentation.registration.RegistrationViewModel

import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val authTokenUseCase: AuthTokenUseCase,
    private val authUseCase: AuthUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        AuthViewModel::class.java -> AuthViewModel(authUseCase, authTokenUseCase) as T
        RegistrationViewModel::class.java -> RegistrationViewModel(registrationUseCase) as T
        ResetPasswordViewModel::class.java -> ResetPasswordViewModel(resetPasswordUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }
}