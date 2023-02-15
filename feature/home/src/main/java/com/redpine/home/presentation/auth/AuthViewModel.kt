package com.redpine.home.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.redpine.home.domain.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _info = MutableStateFlow("")
    val info = _info.asSharedFlow()



    fun getUser() = authRepository.getUser()

    fun createUser(email: String, password: String) =
        authRepository.createUser(email, password).addOnCompleteListener { task ->
            _info.value = if (task.isSuccessful) {
                "успешная регистрация"
            } else "что-то пошло не так "

        }

    fun authUser(email: String, password: String) =
        authRepository.authEmail(email, password).addOnCompleteListener { task ->
            _info.value = if (task.isSuccessful) "успешная авторизация"
            else "что-то пошло не так "
            Log.e("Kart", task.result.user?.uid.toString())
        }

    fun emailVerified() = authRepository.userEmailVerified()

}