package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.core.domain.TokenProvider
import com.redpine.home.domain.repository.OnBoardingRepository
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.presentation.authorization.auth.AuthViewModel
import com.redpine.home.presentation.authorization.registration.RegistrationViewModel
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.filter.FilterViewModel
import com.redpine.home.presentation.found.DogsFoundViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val api: Api,
    private val onBoardingRepository: OnBoardingRepository,
    private val tokenProvider: TokenProvider,
    private val authUseCase: AuthUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(api) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onBoardingRepository) as T
        FilterViewModel::class.java -> FilterViewModel(api) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(api) as T
        AuthViewModel::class.java -> AuthViewModel(authUseCase,tokenProvider) as T
        RegistrationViewModel::class.java -> RegistrationViewModel(registrationUseCase) as T
        ResetPasswordViewModel::class.java -> ResetPasswordViewModel(resetPasswordUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


