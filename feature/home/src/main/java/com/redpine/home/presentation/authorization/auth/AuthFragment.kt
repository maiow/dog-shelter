package com.redpine.home.presentation.authorization.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import com.redpine.core.base.BaseFragmentWithViewModel
import com.redpine.core.component.getComponent
import com.redpine.core.extensions.onTextChanged
import com.redpine.core.state.LoadState
import com.redpine.home.databinding.FragmentAuthBinding
import com.redpine.home.di.component.HomeComponent
import com.redpine.home.presentation.authorization.state.TypeAuthListener

class AuthFragment : BaseFragmentWithViewModel<FragmentAuthBinding,AuthViewModel>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    override fun inject() {
        requireContext().getComponent<HomeComponent>().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validationButton()
        onClickAuthButton()
        onClickNavigationButton(
            binding.resetPasswordButton,
            AuthFragmentDirections.actionAuthFragmentToResetPasswordFragment(),
        )
        onClickNavigationButton(
            binding.registrationButton,
            AuthFragmentDirections.actionAuthFragmentToRegistrationFragment(binding.editEmail.text.toString())
        )
        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }

    }

    private fun validationButton() {
        binding.editEmail.onTextChanged { email ->
            viewModel.validation(email, TypeAuthListener.EMAIL)
        }
        binding.editPassword.onTextChanged { email ->
            viewModel.validation(email, TypeAuthListener.PASSWORD)
        }
    }

    private fun onClickAuthButton() = binding.authButton.setOnClickListener {
        viewModel.startAuth(binding.editEmail.text.toString(), binding.editPassword.text.toString())
    }

    private fun onClickNavigationButton(button: View, direction: NavDirections) =
        button.setOnClickListener {
            navigate(direction)
        }

    private fun loadStateListener(loadState: LoadState) {
        binding.authButton.isEnabled = loadState == LoadState.ENABLE_BUTTON
        binding.registrationButton.isEnabled = loadState != LoadState.LOADING
        binding.authButton.isVisible = loadState != LoadState.LOADING
        binding.progressCircular.isVisible = loadState == LoadState.LOADING
        binding.errorAuthMessage.isVisible = loadState == LoadState.ERROR_AUTH
        binding.errorInternetMessage.isVisible = loadState == LoadState.ERROR_NETWORK
        if (loadState == LoadState.SUCCESS)
            navigate(AuthFragmentDirections.actionAuthFragmentToHomeFragment())
    }
}