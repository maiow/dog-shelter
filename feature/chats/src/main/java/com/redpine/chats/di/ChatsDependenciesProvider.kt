package com.redpine.chats.di

interface ChatsDependenciesProvider {
    var dependencies: ChatsDependencies

    companion object : ChatsDependenciesProvider by ChatsStore
}
