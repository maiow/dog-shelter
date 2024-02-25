package com.redpine.chats.di

import com.redpine.api.Api
import io.getstream.chat.android.client.ChatClient

interface ChatsDependencies {
    val api: Api
    val client: ChatClient
}
