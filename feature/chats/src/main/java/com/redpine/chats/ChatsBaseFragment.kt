package com.redpine.chats

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.redpine.chats.di.ChatsComponentViewModel
import com.redpine.core.base.BaseFragment


abstract class ChatsBaseFragment<B : ViewBinding> : BaseFragment<B>() {

    override fun initViewModelFactory(): ViewModelProvider.Factory =
        ViewModelProvider(this)[ChatsComponentViewModel::class.java]
            .moduleComponent
            .viewModelFactory


}