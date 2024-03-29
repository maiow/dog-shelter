package com.redpine.home.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentOnboardingBinding
import com.redpine.home.presentation.onboarding.adapter.OnBoardingAdapter
import com.redpine.home.presentation.onboarding.view.mediator.ProgressBarLayoutMediator

class OnboardingFragment : HomeBaseFragment<FragmentOnboardingBinding>() {

    private val viewModel by lazy { initViewModel<OnboardingViewModel>() }

    override fun initBinding(inflater: LayoutInflater) = FragmentOnboardingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter by lazy { OnBoardingAdapter() }
        val mediator by lazy {
            ProgressBarLayoutMediator(binding.viewProgress, binding.viewPager)
        }

        setAdapter(adapter, mediator)
        onClickNextButton()
        onClickSkipButton()
        rememberOnBoardingIsShown()

    }

    private fun rememberOnBoardingIsShown() = viewModel.rememberOnBoardingIsShown()

    private fun setAdapter(adapter: OnBoardingAdapter, mediator: ProgressBarLayoutMediator) {
        adapter.setItems(viewModel.list)
        binding.viewPager.adapter = adapter
        mediator.attach()
    }

    private fun onClickNextButton() {
        var clicksCount = 0
        binding.nextButton.setOnClickListener {
            clicksCount++
            binding.viewPager.currentItem++
            if (clicksCount == 3) { navigateToHome() }
        }
    }

    private fun onClickSkipButton() = binding.skip.setOnClickListener { navigateToHome() }

    private fun navigateToHome() {
        findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToHomeNavGraph())
        findNavController().graph.setStartDestination(com.redpine.core.R.id.home_nav_graph)
        // TODO: сделать ресурсы навигации в core
    }
}