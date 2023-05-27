package com.redpine.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.DislikeUseCase
import com.redpine.favorites.presentation.FavoritesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dislikeUseCase: DislikeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        FavoritesViewModel::class.java -> FavoritesViewModel(
            favoritesRepository,
            dislikeUseCase
        ) as T

        else -> throw IllegalAccessError("error create viewModel")
    }
}


