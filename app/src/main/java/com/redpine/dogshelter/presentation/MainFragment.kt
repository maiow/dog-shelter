package com.redpine.dogshelter.presentation

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.redpine.core.base.BaseFragment
import com.redpine.dogshelter.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentMainBinding.inflate(inflater)

    override fun initViewModelFactory(): ViewModelProvider.Factory {
        TODO("Not yet implemented")
    }
}