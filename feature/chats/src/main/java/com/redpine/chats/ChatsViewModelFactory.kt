package com.redpine.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.chats.presentation.ChannelViewModel
import com.redpine.chats.presentation.login.LoginViewModel
import io.getstream.chat.android.client.ChatClient
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ChatsViewModelFactory @Inject constructor(
    private val api: Api,
    private val client: ChatClient
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ChannelViewModel::class.java -> ChannelViewModel(client) as T
        LoginViewModel::class.java -> LoginViewModel(client) as T
        else -> throw IllegalAccessError("error create viewModel")
    }
}
