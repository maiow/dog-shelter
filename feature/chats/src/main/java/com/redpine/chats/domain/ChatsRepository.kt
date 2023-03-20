package com.redpine.chats.domain

import com.redpine.chats.domain.model.Message


interface ChatsRepository {

     fun  getInfo():List<Message>
}