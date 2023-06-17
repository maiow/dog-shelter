package com.redpine.profile.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.profile.domain.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authDialogPrefs: AuthDialogPrefs
) : BaseViewModel() {

    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth.asStateFlow()

    var authDialogIsShown = authDialogPrefs.isShown()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun onRetryButtonClick() {
        TODO()
    }

    fun checkAuth() {
        viewModelScope.launch(Dispatchers.IO){
            _isAuth.value = profileRepository.isUserAuthorized()
            authDialogIsShown = authDialogPrefs.isShown()
            if (_isAuth.value) {
                _email.value = profileRepository.getEmail()
            }
        }
    }

    fun resetAuthCheck() {
        _isAuth.value = false
    }

    fun rememberAuthDialogIsShown() {
        authDialogPrefs.rememberAuthDialogIsShown()
    }
}