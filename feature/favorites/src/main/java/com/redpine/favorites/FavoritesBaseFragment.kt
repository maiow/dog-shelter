package com.redpine.favorites

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.core.base.BaseFragment
import com.redpine.favorites.di.FavoritesComponentViewModel


abstract class FavoritesBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(this)[FavoritesComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory


}