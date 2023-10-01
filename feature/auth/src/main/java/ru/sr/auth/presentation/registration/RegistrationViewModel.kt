package ru.sr.auth.presentation.registration

import com.redpine.core.base.BaseViewModel
import com.redpine.core.extensions.emailValidation
import com.redpine.core.extensions.passwordValidation
import com.redpine.core.state.LoadState
import ru.sr.auth.domain.usecase.RegistrationUseCase
import ru.sr.auth.presentation.state.TypeAuthListener
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
) : BaseViewModel() {

    private var isEmailValidation = false
    private var isPasswordValidation = false
    private var repeatPassword = ""
    private var password = ""

    fun createNewUser(email: String, password: String) =
        scopeLaunch {
             registrationUseCase.createUser(email, password)
        }

    fun validation(text: String, type: TypeAuthListener, text2: String = "") {
        when (type) {
            TypeAuthListener.EMAIL -> emailValidation(text)
            TypeAuthListener.PASSWORD -> passwordValidation(text, text2)
        }
        _loadState.value = if (isEmailValidation && isPasswordValidation) LoadState.ENABLE_BUTTON
        else LoadState.START
    }

    private fun emailValidation(email: String) {
        isEmailValidation = email.emailValidation()
    }

    private fun passwordValidation(password: String, repeatPassword: String) {
        this.password = password
        this.repeatPassword = repeatPassword
        isPasswordValidation = password.passwordValidation() && password == repeatPassword
    }
}