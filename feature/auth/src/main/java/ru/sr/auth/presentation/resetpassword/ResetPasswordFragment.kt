package ru.sr.auth.presentation.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.redpine.auth.databinding.FragmentResetPasswordBinding
import com.redpine.core.extensions.onTextChanged
import com.redpine.core.state.LoadState
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordViewModel
import ru.sr.auth.presentation.state.TypeAuthListener
import ru.sr.auth.presentation.AuthBaseFragment

class ResetPasswordFragment : AuthBaseFragment<FragmentResetPasswordBinding>() {
    private val viewModel: ResetPasswordViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) =
        FragmentResetPasswordBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }

        binding.editEmail.onTextChanged { email ->
            viewModel.validation(email, TypeAuthListener.EMAIL)
        }

        binding.authButton.setOnClickListener {
            viewModel.resetPassword( binding.editEmail.text.toString())
        }
    }

    private fun loadStateListener(loadState: LoadState) {
        binding.authButton.isEnabled = loadState == LoadState.ENABLE_BUTTON
        binding.authButton.isVisible = loadState != LoadState.LOADING
        binding.progressCircular.isVisible = loadState == LoadState.LOADING
        binding.errorAuthMessage.isVisible = loadState == LoadState.ERROR_AUTH
        binding.errorInternetMessage.isVisible = loadState == LoadState.ERROR_NETWORK
        if (loadState == LoadState.SUCCESS)
            navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToAuthMessageFragment())
    }
}