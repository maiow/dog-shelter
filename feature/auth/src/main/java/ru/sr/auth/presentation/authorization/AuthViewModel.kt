package ru.sr.auth.presentation.authorization

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.state.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sr.auth.domain.AuthGoogleRepository
import ru.sr.auth.domain.usecase.AuthEmailAndPasswordUseCase
import ru.sr.auth.domain.usecase.AuthTokenUseCase
import ru.sr.auth.presentation.state.AuthState
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthEmailAndPasswordUseCase,
    private val tokenProvider: AuthTokenUseCase,
    private val authGoogleRepository: AuthGoogleRepository
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState())
    val viewState = _viewState.asStateFlow()

    fun startEmailAuth(email: String, password: String) = scopeLaunch {
        val token = authUseCase.authEmail(email, password).toString()
        tokenProvider.putToken(token)
    }

    fun validation(email: String) {
        _loadState.value = if (email.emailValidation()) LoadState.ENABLE_BUTTON
        else LoadState.START
    }


    fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            authGoogleRepository.signIn()
                .onSuccess { intentSender ->
                    _viewState.update { state ->
                        state.copy(intentSender = intentSender)
                    }
                }
                .onFailure { exception: Throwable ->
                    exception.printStackTrace()
                }
        }
    }

    fun signWithIntentUseCase(intent: Intent) {

        scopeLaunch {
            authGoogleRepository.signWithIntent(intent)
                .onSuccess { token ->
                    tokenProvider.putToken(token)
                }
                .onFailure { exception: Throwable ->
                    exception.printStackTrace()
                }
        }
    }
}

