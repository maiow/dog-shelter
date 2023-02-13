package com.redpine.home.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.redpine.home.domain.OnboardingRepository
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository,
) : ViewModel() {

    val list = repository.getInfo()

}