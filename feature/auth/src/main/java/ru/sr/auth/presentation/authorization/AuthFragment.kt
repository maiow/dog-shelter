package ru.sr.auth.presentation.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.redpine.auth.databinding.FragmentAuthBinding
import com.redpine.core.extensions.onTextChanged
import com.redpine.core.state.LoadState
import ru.sr.auth.presentation.AuthBaseFragment

class AuthFragment : AuthBaseFragment<FragmentAuthBinding>() {

    private val viewModel: AuthViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validationButton()
        onClickAuthButton()
        onClickNavigationButton(
            button = binding.resetPasswordButton,
            direction = AuthFragmentDirections.actionAuthFragmentToResetPasswordFragment()
        )

        onClickNavigationButton(
            button = binding.registrationButton,
            direction = AuthFragmentDirections.actionAuthFragmentToRegistrationFragment(binding.editEmail.text.toString())
        )
        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }

    }

    private fun validationButton() = binding.editEmail.onTextChanged { email ->
        viewModel.validation(email)
    }


    private fun onClickAuthButton() = binding.authButton.setOnClickListener {
        viewModel.startAuth(binding.editEmail.text.toString(), binding.editPassword.text.toString())
    }

    private fun onClickNavigationButton(button: View, direction: NavDirections) =
        button.setOnClickListener {
            navigate(direction)
        }

    private fun loadStateListener(loadState: LoadState) {
        binding.apply {
            authButton.isEnabled = loadState == LoadState.ENABLE_BUTTON
            registrationButton.isEnabled = loadState != LoadState.LOADING
            authButton.isVisible = loadState != LoadState.LOADING
            progressCircular.isVisible = loadState == LoadState.LOADING
            errorAuthMessage.isVisible = loadState == LoadState.ERROR_AUTH
            errorInternetMessage.isVisible = loadState == LoadState.ERROR_NETWORK
        }
        if (loadState == LoadState.SUCCESS)
            findNavController().popBackStack()
    }
}