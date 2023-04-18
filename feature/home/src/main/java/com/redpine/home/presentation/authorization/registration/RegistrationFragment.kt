package com.redpine.home.presentation.authorization.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.redpine.core.extensions.onTextChanged
import com.redpine.core.state.LoadState
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentRegistrationBinding
import com.redpine.home.presentation.authorization.state.TypeAuthListener

class RegistrationFragment : HomeBaseFragment<FragmentRegistrationBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentRegistrationBinding.inflate(inflater)

    private val viewModel: RegistrationViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validationButton()

        onClickRegistration()

        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }
    }

    private fun onClickRegistration()=
        binding.registrationButton.setOnClickListener {
            viewModel.createNewUser(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
    }

    private fun loadStateListener(loadState: LoadState) {
        binding.registrationButton.isEnabled = loadState == LoadState.ENABLE_BUTTON
        binding.registrationButton.isVisible = loadState != LoadState.LOADING
        binding.progressCircular.isVisible = loadState == LoadState.LOADING
        binding.errorRegistrationMessage.isVisible = loadState == LoadState.ERROR_AUTH
        binding.errorInternetMessage.isVisible = loadState == LoadState.ERROR_NETWORK
        if (loadState == LoadState.SUCCESS)
            navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAuthMessageFragment())
    }

    private fun validationButton() {
        binding.editEmail.onTextChanged { email ->
            viewModel.validation(email, TypeAuthListener.EMAIL)
        }
        binding.editPassword.onTextChanged { password ->
            viewModel.validation(
                password,
                TypeAuthListener.PASSWORD,
                binding.editRepeatPassword.text.toString()
            )
        }
        binding.editRepeatPassword.onTextChanged { repeatPassword ->
            viewModel.validation(
                repeatPassword,
                TypeAuthListener.PASSWORD,
                binding.editPassword.text.toString()
            )
        }
    }
}