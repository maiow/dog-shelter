package com.redpine.chats.di

import com.redpine.core.base.ComponentViewModel

class ChatsComponentViewModel : ComponentViewModel() {

    override val moduleComponent = DaggerChatsComponent
        .builder()
        .dependencies(ChatsDependenciesProvider.dependencies)
        .build()
}

