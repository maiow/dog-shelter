package com.redpine.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.profile.domain.usecase.DeleteAccountUseCase
import com.redpine.profile.domain.usecase.LogoutUseCase
import com.redpine.profile.domain.usecase.ProfileInfoUseCase
import com.redpine.profile.presentation.profile.ProfileViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory @Inject constructor(
    private val profileInfoUseCase: ProfileInfoUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ProfileViewModel::class.java -> ProfileViewModel(
            profileInfoUseCase,
            deleteAccountUseCase,
            logoutUseCase,
        ) as T

        else -> throw IllegalAccessError("error create viewModel")
    }
}


