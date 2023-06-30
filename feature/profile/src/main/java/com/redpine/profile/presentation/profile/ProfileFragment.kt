package com.redpine.profile.presentation.profile

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.redpine.core.state.LoadState
import com.redpine.profile.ProfileBaseFragment
import com.redpine.profile.presentation.profile.ProfileViewModel.UserActionResult
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
            showDeleteAccountDialog()
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

        with(binding){
            reenterPassword.isVisible = result == UserActionResult.REAUTH_FAILED
            layoutPassword.isVisible = result == UserActionResult.REAUTH_FAILED
        }
    }

    private fun observeAuth(auth: Boolean) {
        with(binding) {
            layoutEmail.isVisible = auth
            logoutButton.isVisible = auth
            deleteAccountButton.isVisible = auth
            authButton.isVisible = !auth
            notLoggedText.isVisible = !auth
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
            layoutPassword.isVisible = false
            reenterPassword.isVisible = false
        }
    }

    private fun showDeleteAccountDialog() {
        val authDialog = MaterialAlertDialogBuilder(requireContext()).create()
        authDialog.apply {
            setTitle(getString(R.string.delete_account_dialog_title))
            setMessage(getString(R.string.delete_account_dialog))
            setButton(
                AlertDialog.BUTTON_NEGATIVE, getString(com.redpine.core.R.string.auth_dialog_cancel)
            ) { dialog, _ ->
                dialog.dismiss()
            }
            setButton(
                AlertDialog.BUTTON_POSITIVE, getString(R.string.delete)
            ) { _, _ ->
                reenterPassword()
            }
            show()
        }
    }

    private fun reenterPassword() {
        with(binding) {
            reenterPassword.isVisible = true
            layoutPassword.isVisible = true
            editPassword.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        viewModel.deleteAccount(editPassword.text.toString())
                        editPassword.clearFocus()
                        editPassword.isCursorVisible = false
                        return true
                    }
                    return false
                }
            })
        }
    }
}