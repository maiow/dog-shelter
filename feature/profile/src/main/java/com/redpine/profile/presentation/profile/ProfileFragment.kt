package com.redpine.profile.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.redpine.core.state.LoadState
import com.redpine.profile.ProfileBaseFragment
import com.redpine.profiler.R
import com.redpine.profiler.databinding.FragmentProfileBinding

class ProfileFragment : ProfileBaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkAuth()
        flowObserver(viewModel.email) { email -> setUserInterface(email) }
        binding.helpDogsButton.setOnClickListener {
            navigate(R.id.actionProfileToHelp)
        }
    }

    private fun loadingObserve(loadState: LoadState) {
//        with(binding) {
//            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
//            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
//            connectionError.retryButton.setOnClickListener {
//                viewModel.onRetryButtonClick()
//            }
//        }
    }

    private fun setUserInterface(email: String) {
        with(binding) {
            layoutEmail.hint =
                if (viewModel.isAuth.value) email
                else "pls login"
            saveDataButton.isVisible = false
            layoutPassword.isVisible = false
        }

    }

}