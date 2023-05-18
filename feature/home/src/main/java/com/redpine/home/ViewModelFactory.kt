package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.OnboardingRepository
import com.redpine.home.domain.usecase.AuthTokenUseCase
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import com.redpine.home.domain.usecase.ListNewsUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.domain.usecase.SingleNewsUseCase
import com.redpine.home.presentation.authorization.auth.AuthViewModel
import com.redpine.home.presentation.authorization.registration.RegistrationViewModel
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordViewModel
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
    private val authTokenUseCase: AuthTokenUseCase,
    private val authUseCase: AuthUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val onboardingRepository: OnboardingRepository,
    private val onBoardingPrefs: OnBoardingPrefs,
    private val homeScreenUseCase: HomeScreenUseCase,
    private val listNewsUseCase: ListNewsUseCase,
    private val singleNewsUseCase: SingleNewsUseCase,
    private val dogsRepository: DogsRepository,
    private val likeUseCase: LikeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(homeScreenUseCase, likeUseCase) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        FilterViewModel::class.java -> FilterViewModel(api) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(api) as T
        PetsCardViewModel::class.java -> PetsCardViewModel(dogsRepository, likeUseCase) as T
        NewsListViewModel::class.java -> NewsListViewModel(listNewsUseCase) as T
        SingleNewsViewModel::class.java -> SingleNewsViewModel(singleNewsUseCase) as T

        AuthViewModel::class.java -> AuthViewModel(authUseCase, authTokenUseCase) as T
        RegistrationViewModel::class.java -> RegistrationViewModel(registrationUseCase) as T
        ResetPasswordViewModel::class.java -> ResetPasswordViewModel(resetPasswordUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


