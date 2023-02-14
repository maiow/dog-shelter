package com.redpine.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentHomeBinding

class HomeFragment : HomeBaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel: HomeViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test.text = viewModel.getText()
    }

}