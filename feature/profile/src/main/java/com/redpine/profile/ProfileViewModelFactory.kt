package com.redpine.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.presentation.profile.ProfileViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authDialogPrefs: AuthDialogPrefs
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ProfileViewModel::class.java -> ProfileViewModel(
            profileRepository,
            authDialogPrefs
        ) as T

        else -> throw IllegalAccessError("error create viewModel")
    }
}


