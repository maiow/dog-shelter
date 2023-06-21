package com.redpine.profile.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.redpine.core.state.LoadState
import com.redpine.profile.ProfileBaseFragment
import com.redpine.profile.presentation.UserActionResult
import com.redpine.profiler.R
import com.redpine.profiler.databinding.FragmentProfileBinding

class ProfileFragment : ProfileBaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkAuth()
        flowObserver(viewModel.email) { email -> setUserInterface(email) }
        flowObserver(viewModel.isAuth) { isAuth -> observeAuth(isAuth) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.actionResult) { result -> collectActionResult(result) }
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.helpDogsButton.setOnClickListener {
            navigate(R.id.actionProfileToHelp)
        }
        binding.deleteAccountButton.setOnClickListener {
            // TODO: collect email and password or just password
            viewModel.deleteAccount("email", "password")
        }
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
        binding.authButton.setOnClickListener {
            navigate(R.id.actionProfileToAuth)
        }
    }

    private fun collectActionResult(result: UserActionResult) {
        val text = when (result) {
            UserActionResult.LOGOUT -> getString(R.string.you_have_logged_out)
            UserActionResult.REAUTH_FAILED -> getString(R.string.reauthentication_failed)
            UserActionResult.ACCOUNT_DELETED -> getString(R.string.account_deleted)
            UserActionResult.ERROR -> getString(R.string.some_error_occurred)
        }
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
    }

    private fun observeAuth(auth: Boolean) {
        if (!auth) observeAuthDialogIsShown(viewModel.authDialogIsShown)

        with(binding) {
            layoutEmail.isVisible = auth
            logoutButton.isVisible = auth
            deleteAccountButton.isVisible = auth
            authButton.isVisible = !auth
            notLoggedText.isVisible = !auth
        }
    }

    private fun observeAuthDialogIsShown(isShown: Boolean) {
        if (!isShown) {
            showAuthDialog(R.id.actionProfileToAuth) { viewModel.resetAuthCheck() }
            viewModel.rememberAuthDialogIsShown()
        } else {
            viewModel.resetAuthCheck()
        }
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.onRetryButtonClick()
            }
        }
    }

    private fun setUserInterface(email: String) {
        with(binding) {
            layoutEmail.hint = email
            /**пока эти кнопки скрыты всегда, переписать для передачи пароля при удалении*/
            saveDataButton.isVisible = false
            layoutPassword.isVisible = false
        }
    }
}