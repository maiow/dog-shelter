package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.home.domain.OnboardingRepository
import com.redpine.home.domain.Repository
import com.redpine.core.domain.TokenProvider
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.presentation.authorization.auth.AuthViewModel
import com.redpine.home.presentation.authorization.registration.RegistrationViewModel
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.filter.FilterViewModel
import com.redpine.home.presentation.found.DogsFoundViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.news.NewsListViewModel
import com.redpine.home.presentation.news.SingleNewsViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import com.redpine.home.presentation.pets_card.PetsCardViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val api: Api,
    private val tokenProvider: TokenProvider,
    private val authUseCase: AuthUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val onboardingRepository: OnboardingRepository,
    private val repository: Repository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(repository) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        HomeViewModel::class.java -> HomeViewModel(api) as T
        FilterViewModel::class.java -> FilterViewModel(api) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(api) as T
        PetsCardViewModel::class.java -> PetsCardViewModel(api) as T
        NewsListViewModel::class.java -> NewsListViewModel(repository) as T
        SingleNewsViewModel::class.java -> SingleNewsViewModel(repository) as T

        AuthViewModel::class.java -> AuthViewModel(authUseCase,tokenProvider) as T
        RegistrationViewModel::class.java -> RegistrationViewModel(registrationUseCase) as T
        ResetPasswordViewModel::class.java -> ResetPasswordViewModel(resetPasswordUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


