package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.home.domain.OnboardingRepository
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.filter.FilterViewModel
import com.redpine.home.presentation.found.DogsFoundViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import com.redpine.home.presentation.pets_card.PetsCardViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val api: Api,
    private val onboardingRepository: OnboardingRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(api) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        FilterViewModel::class.java -> FilterViewModel(api) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(api) as T
        PetsCardViewModel::class.java -> PetsCardViewModel(api) as T
        else -> throw IllegalAccessError("ViewModel creating error")
    }


}


