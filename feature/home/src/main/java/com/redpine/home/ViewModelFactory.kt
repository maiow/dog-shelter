package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.home.domain.repository.OnBoardingRepository
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.presentation.authorization.auth.AuthViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.filter.FilterViewModel
import com.redpine.home.presentation.found.DogsFoundViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val api: Api,
    private val onboardingRepository: OnBoardingRepository,
    private val authUseCase: AuthUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(api) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        FilterViewModel::class.java -> FilterViewModel(api) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(api) as T
        AuthViewModel::class.java -> AuthViewModel(authUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


