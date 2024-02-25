package com.redpine.chats.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.chats.ChatsViewModelFactory
import dagger.Module

@Module
interface Binds {

    @dagger.Binds
    fun bindsViewModelFactory(viewModelFactory: ChatsViewModelFactory): ViewModelProvider.Factory
}
