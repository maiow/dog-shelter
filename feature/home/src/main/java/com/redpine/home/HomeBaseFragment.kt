package com.redpine.home

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.redpine.core.base.BaseFragment
import com.redpine.home.di.HomeComponentViewModel


abstract class HomeBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModelFactory<HomeComponentViewModel>(this)
    }

}