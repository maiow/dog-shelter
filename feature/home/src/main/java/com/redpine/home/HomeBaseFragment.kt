package com.redpine.home

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.core.base.BaseFragment
import com.redpine.home.di.componentviewmodel.HomeComponentViewModel


abstract class HomeBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(this)[HomeComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory


}