package com.redpine.profile.presentation.profile

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.profile.domain.usecase.DeleteAccountUseCase
import com.redpine.profile.domain.usecase.LogoutUseCase
import com.redpine.profile.domain.usecase.ProfileInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileInfoUseCase: ProfileInfoUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _actionResult = MutableSharedFlow<UserActionResult>()
    val actionResult = _actionResult.asSharedFlow()

    fun onRetryButtonClick() = checkAuth()

    fun checkAuth() {
        viewModelScope.launch(Dispatchers.IO) {
            _isAuth.value = profileInfoUseCase.isUserAuthorized()
            if (_isAuth.value) _email.value = profileInfoUseCase.getEmail()
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            if (logoutUseCase.logout()) {
                _actionResult.emit(UserActionResult.LOGOUT)
                _email.value = LOGGED_OUT
                _isAuth.value = false
            }
        }
    }

    fun deleteAccount(password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!deleteAccountUseCase.reauthenticateUser(password))
                _actionResult.emit(UserActionResult.REAUTH_FAILED)
            else if (deleteAccountUseCase.deleteAccount()) {
                _actionResult.emit(UserActionResult.ACCOUNT_DELETED)
                _isAuth.value = false
            } else _actionResult.emit(UserActionResult.ERROR)
        }
    }

    enum class UserActionResult {
        LOGOUT,
        REAUTH_FAILED,
        ACCOUNT_DELETED,
        ERROR
    }

    companion object {
        const val LOGGED_OUT = "You have logged out"
    }
}