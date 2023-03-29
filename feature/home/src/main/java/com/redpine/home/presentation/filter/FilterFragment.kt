package com.redpine.home.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentFilterBinding

class FilterFragment : HomeBaseFragment<FragmentFilterBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilterBinding.inflate(inflater)
   // private val viewModel: FilterViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.test.text = viewModel.getText()
    }

}