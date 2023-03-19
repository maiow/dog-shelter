package com.redpine.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.presentation.FavoritesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
    private val api: Api,
    private val favoritesRepository: FavoritesRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        FavoritesViewModel::class.java -> FavoritesViewModel(api, favoritesRepository) as T
        //FavoritesSecondViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


