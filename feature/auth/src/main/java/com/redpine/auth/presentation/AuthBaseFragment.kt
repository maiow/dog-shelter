package com.redpine.auth.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.auth.di.componentviewmodel.AuthComponentViewModel
import com.redpine.core.base.BaseFragment

abstract class AuthBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(requireActivity())[AuthComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory

}