package com.redpine.chats.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.redpine.chats.ChatsBaseFragment
import com.redpine.chats.R
import com.redpine.chats.databinding.FragmentLoginBinding
import com.redpine.chats.util.Constants
import com.redpine.chats.util.navigateSafely

class LoginFragment : ChatsBaseFragment<FragmentLoginBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentLoginBinding.inflate(inflater)
    private val viewModel: LoginViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkIfAlreadyLogged()
        binding.btnConfirm.setOnClickListener {
            setupConnectingUiState()
            viewModel.connectUser(binding.etUsername.text.toString())
        }

        binding.etUsername.addTextChangedListener {
            binding.etUsername.error = null
        }

        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect { event ->
                when (event) {
                    is LoginViewModel.LogInEvent.ErrorInputTooShort -> {
                        setupIdleUiState()
                        binding.etUsername.error = getString(
                            R.string.error_username_too_short,
                            Constants.MIN_USERNAME_LENGTH
                        )
                    }

                    is LoginViewModel.LogInEvent.ErrorLogIn -> {
                        setupIdleUiState()
                        Toast.makeText(
                            requireContext(),
                            event.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is LoginViewModel.LogInEvent.Success -> {
                        setupIdleUiState()
                        findNavController().navigateSafely(
                            R.id.action_loginFragment_to_chatsFragment
                        )
                    }
                }
            }
        }
    }

    private fun setupConnectingUiState() {
        binding.progressBar.isVisible = true
        binding.btnConfirm.isEnabled = false
    }

    private fun setupIdleUiState() {
        binding.progressBar.isVisible = false
        binding.btnConfirm.isEnabled = true
    }
}
