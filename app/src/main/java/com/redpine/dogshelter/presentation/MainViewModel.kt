package com.redpine.dogshelter.presentation

import androidx.lifecycle.ViewModel
import com.redpine.core.domain.OnBoardingPrefs
import javax.inject.Inject

class MainViewModel @Inject constructor(
    onBoardingPrefs: OnBoardingPrefs
): ViewModel() {

    val isOnboardingShown = onBoardingPrefs.isShown()
}