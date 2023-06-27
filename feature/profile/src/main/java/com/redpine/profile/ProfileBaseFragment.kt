package com.redpine.profile

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.core.base.BaseFragment
import com.redpine.profile.di.ProfileComponentViewModel


abstract class ProfileBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(requireActivity())[ProfileComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory


}