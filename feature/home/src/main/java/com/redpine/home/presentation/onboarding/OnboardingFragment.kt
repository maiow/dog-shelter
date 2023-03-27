package com.redpine.home.presentation.onboarding

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentOnboardingBinding
import com.redpine.home.presentation.onboarding.adapter.OnBoardingAdapter
import com.redpine.home.presentation.onboarding.view.mediator.ProgressBarLayoutMediator

class OnboardingFragment : HomeBaseFragment<FragmentOnboardingBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentOnboardingBinding.inflate(inflater)

    private val viewModel by lazy { initViewModel<OnboardingViewModel>() }

    private val adapter by lazy { OnBoardingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        onClickNextButton()

        onClickSkipButton()

        hideNextButtonOnLowScreens()
    }

    private fun setAdapter() {
        adapter.setItems(viewModel.list)
        binding.viewPager.adapter = adapter
        ProgressBarLayoutMediator(binding.viewProgress, binding.viewPager).attach()
    }

    private fun onClickNextButton() {
        binding.nextButton.setOnClickListener {
            binding.viewPager.currentItem++
        }
    }

    private fun onClickSkipButton() {
        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }
    }

    private fun hideNextButtonOnLowScreens() {
        if (Resources.getSystem().displayMetrics.heightPixels < 1500)
            binding.nextButton.visibility = View.GONE
    }
}