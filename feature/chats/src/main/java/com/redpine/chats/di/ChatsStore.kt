package com.redpine.chats.di

import kotlin.properties.Delegates

object ChatsStore : ChatsDependenciesProvider {
    override var dependencies: ChatsDependencies by Delegates.notNull()
}