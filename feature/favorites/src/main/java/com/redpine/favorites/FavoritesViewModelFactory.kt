package com.redpine.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.favorites.domain.usecase.DislikeUseCase
import com.redpine.favorites.domain.usecase.FavoritesUseCase
import com.redpine.favorites.domain.usecase.SearchUseCase
import com.redpine.favorites.presentation.FavoritesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase,
    private val dislikeUseCase: DislikeUseCase,
    private val searchUseCase: SearchUseCase,
    private val authDialogPrefs: AuthDialogPrefs,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        FavoritesViewModel::class.java -> FavoritesViewModel(
            favoritesUseCase,
            dislikeUseCase,
            searchUseCase,
            authDialogPrefs
        ) as T

        else -> throw IllegalAccessError("error create viewModel")
    }
}


