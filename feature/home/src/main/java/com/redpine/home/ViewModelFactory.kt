package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.home.domain.AuthRepository
import com.redpine.home.domain.OnboardingRepository
import com.redpine.home.presentation.auth.AuthViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val api: Api,
    private val onboardingRepository: OnboardingRepository,
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(api) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        AuthViewModel::class.java -> AuthViewModel(authRepository) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


