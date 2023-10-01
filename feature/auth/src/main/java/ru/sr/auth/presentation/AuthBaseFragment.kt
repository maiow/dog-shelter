package ru.sr.auth.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.core.base.BaseFragment
import ru.sr.auth.di.componentviewmodel.AuthComponentViewModel


abstract class AuthBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(requireActivity())[AuthComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory

}