package com.redpine.auth.presentation.auth

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.redpine.auth.R
import com.redpine.auth.databinding.FragmentAuthBinding
import com.redpine.auth.presentation.AuthBaseFragment
import com.redpine.core.extensions.onTextChanged
import com.redpine.core.state.LoadState
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.xml.OneTap
import kotlinx.coroutines.launch

class AuthFragment : AuthBaseFragment<FragmentAuthBinding>() {

    private val viewModel: AuthViewModel by lazy { initViewModel() }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.signWithIntentUseCase(result.data ?: return@launch)
                    navigate(AuthFragmentDirections.actionAuthFragmentToHomeNavGraph())
                }
            }
        }

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        validationButton()
        onClickAuthButton()

        binding.authButtonGoogle.setOnClickListener {
            viewModel.signInG()
        }

        val vkidButton = view.findViewById<OneTap>(R.id.vkidButton)
        vkidButton.authParams = VKIDAuthUiParams { scopes = setOf("email") }

        vkidButton.setCallbacks(
            onAuth = { _, _ ->
                Log.d("Kart", "Auth")
            },
            onFail = { _, fail ->
//                onLoginFailed(fail)

//                    when (fail) {
//                        is VKIDAuthFail.Canceled -> TODO()
//                        is VKIDAuthFail.FailedApiCall -> TODO()
//                        is VKIDAuthFail.FailedOAuthState -> TODO()
//                        is VKIDAuthFail.FailedRedirectActivity -> TODO()
//                        is VKIDAuthFail.NoBrowserAvailable -> TODO()
//                        is VKIDAuthFail.FailedOAuth -> TODO()
//                    }
                Log.d("Kart", "Fail is $fail")
            }
        )

        onClickNavigationButton(
            button = binding.resetPasswordButton,
            direction = AuthFragmentDirections.actionAuthFragmentToResetPasswordFragment()
        )

        onClickNavigationButton(
            button = binding.registrationButton,
            direction = AuthFragmentDirections.actionAuthFragmentToRegistrationFragment(binding.editEmail.text.toString())
        )
        flowObserver(viewModel.loadState) { loadState -> loadStateListener(loadState) }
        flowObserver(viewModel.viewState) { viewState ->
            Log.e("Kart", "$viewState")
            viewState.intentSender?.let { intentSender ->
                launcher.launch(
                    IntentSenderRequest.Builder(intentSender).build()
                )
            }
        }
    }

    //TODO: rewrite with onetap

//        private fun onLoginFailed(fail: VKIDAuthFail) {
//        if (!fail.description.isNullOrEmpty()) {
//            val descriptionResource =
////                if (exception.webViewError == WebViewClient.ERROR_HOST_LOOKUP) "R.string.message_connection_error"
//                /*else*/ "R.string.message_unknown_error"
//            AlertDialog.Builder(requireActivity())
//                .setMessage(descriptionResource)
//                .setPositiveButton(
//                    com.redpine.core.R.string.retry
//                ) { _, _ ->
//                    vkAuthLauncher.launch(arrayListOf(VKScope.EMAIL))
//                }
//                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .show()
//        }
//    }

    private fun validationButton() = binding.editEmail.onTextChanged { email ->
        viewModel.validation(email)
    }

    private fun onClickAuthButton() = binding.authButton.setOnClickListener {
        viewModel.startEmailAuth(
            binding.editEmail.text.toString(),
            binding.editPassword.text.toString()
        )
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