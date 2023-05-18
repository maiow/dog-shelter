package com.redpine.home.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.home.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    repository: OnboardingRepository,
    private val onBoardingPrefs: OnBoardingPrefs
) : ViewModel() {

    val list = repository.getInfo()

    fun rememberOnBoardingIsShown(){
        onBoardingPrefs.rememberOnBoardingIsShown()
    }

}