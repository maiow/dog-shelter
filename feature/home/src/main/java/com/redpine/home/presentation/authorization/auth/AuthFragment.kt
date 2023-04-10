package com.redpine.home.presentation.authorization.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.redpine.core.state.LoadState
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentAuthBinding
import com.redpine.home.presentation.authorization.state.TypeAuthListener

class AuthFragment : HomeBaseFragment<FragmentAuthBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    private val viewModel: AuthViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textChangeListener(binding.editEmail, TypeAuthListener.EMAIL)
        textChangeListener(binding.editPassword, TypeAuthListener.PASSWORD)

        onClickAuthButton()

        onClickNavigationButton(
            binding.resetPasswordButton,
            AuthFragmentDirections
                .actionAuthFragmentToResetPasswordFragment(binding.editEmail.toString()),
        )

        onClickNavigationButton(
            binding.registrationButton,
            AuthFragmentDirections
                .actionAuthFragmentToRegistrationFragment(binding.editEmail.toString()),
        )

        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }

    }

    private fun textChangeListener(edittext: TextInputEditText, type: TypeAuthListener) =
        edittext.doOnTextChanged { text, _, _, _ ->
            viewModel.validation(text.toString(), type)
        }

    private fun onClickAuthButton() = binding.authButton.setOnClickListener {
        viewModel.startAuth(binding.editEmail.text.toString(), binding.editPassword.text.toString())
    }

    private fun onClickNavigationButton(button: View, direction: NavDirections) =
        button.setOnClickListener {
            navigate(direction)
        }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
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
