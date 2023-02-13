package com.redpine.home.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.redpine.home.HomeBaseFragment
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

    }

    private fun onClickSkipButton() {
        binding.skip.setOnClickListener {
            Toast.makeText(requireContext(), "навигация", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter(){
        adapter.setItems(viewModel.list)
        binding.viewPager.adapter = adapter
        ProgressBarLayoutMediator(binding.viewProgress, binding.viewPager).attach()
    }

    private fun onClickNextButton(){
        binding.nextButton.setOnClickListener {
            binding.viewPager.currentItem ++
        }
    }

}