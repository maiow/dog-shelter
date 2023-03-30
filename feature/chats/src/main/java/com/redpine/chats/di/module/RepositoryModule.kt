package com.redpine.chats.di.module

import com.redpine.chats.data.ChatsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesChatsRepository() = ChatsRepositoryImpl()
}