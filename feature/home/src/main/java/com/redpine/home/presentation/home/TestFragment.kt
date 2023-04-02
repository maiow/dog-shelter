package com.redpine.home.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.databinding.FragmentTestBinding

class TestFragment: HomeBaseFragment<FragmentTestBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentTestBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}