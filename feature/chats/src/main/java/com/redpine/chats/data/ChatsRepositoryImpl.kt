package com.redpine.chats.data

import com.redpine.chats.domain.ChatsRepository
import com.redpine.chats.domain.model.Message


class ChatsRepositoryImpl : ChatsRepository {

    private val m1 = Message("me", "hello")
    private val m2 = Message("friend", "how you doing?")
    private val list = listOf(m1, m2)

    override fun getInfo() = list
}