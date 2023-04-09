package com.redpine.home.presentation.authorization.auth

import android.util.Log
import com.redpine.core.base.BaseViewModel
import com.redpine.home.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    fun startAuth(email: String, password: String) =
        scopeAction {
            Log.e("kart", authUseCase.authEmail(email, password).toString())
        }

}