package com.redpine.chats.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.chats.ChatsViewModelFactory
import com.redpine.chats.data.ChatsRepositoryImpl
import com.redpine.chats.domain.ChatsRepository
import dagger.Module

@Module
interface Binds {

    @dagger.Binds
    fun bindsChatsRepository(repositoryImpl: ChatsRepositoryImpl): ChatsRepository

    @dagger.Binds
    fun bindsViewModelFactory(viewModelFactory: ChatsViewModelFactory): ViewModelProvider.Factory

}