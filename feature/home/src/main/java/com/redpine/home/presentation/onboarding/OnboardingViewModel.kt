package com.redpine.home.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.redpine.home.domain.repository.OnBoardingRepository
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    repository: OnBoardingRepository,
) : ViewModel() {

    val list = repository.getInfo()

}