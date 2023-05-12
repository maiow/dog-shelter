package com.redpine.dogshelter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.core.domain.OnBoardingPrefs
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val onBoardingPrefs: OnBoardingPrefs,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(onBoardingPrefs) as T
    }
}