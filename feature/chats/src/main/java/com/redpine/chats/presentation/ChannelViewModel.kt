package com.redpine.chats.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ChannelViewModel @Inject constructor(
    private val client: ChatClient
) : BaseViewModel() {

    private val _createChannelEvent = MutableSharedFlow<CreateChannelEvent>()
    val createChannelEvent = _createChannelEvent.asSharedFlow()


    internal fun connectUser(user: User) {
        val token = client.devToken(user.id)

        //проверка законнектился уже или еще нет, подключаем только новых:
        if (client.getCurrentUser()?.id != user.id)
            client.connectUser(user = user, token = token) // Replace with a real token string
                .enqueue()
        //            .enqueue { result ->
        //                if (result.isSuccess) {
        //                    // Handle success
        //                } else {
        //                    // Handle error
        //                }
        //            }
    }

    fun createChannel(channelName: String) {
        val trimmedChannelName = channelName.trim()
        viewModelScope.launch {
            if (trimmedChannelName.isEmpty()) {
                _createChannelEvent.emit(CreateChannelEvent.Error("The channel name can't be empty."))
                return@launch
            }
            val result = client.channel(
                channelType = "messaging",
                channelId = UUID.randomUUID().toString()
            ).create(
                listOf(getUser()!!.id, "redpet"),
                mapOf(
                    "name" to trimmedChannelName,
                )
            ).await()
            if (result.isError) {
                _createChannelEvent.emit(
                    CreateChannelEvent.Error(result.error().message ?: "Unknown error")
                )
                return@launch
            }
            _createChannelEvent.emit(CreateChannelEvent.Success)
        }
    }

    fun logout() {
        client.disconnect(false)
    }

    fun getUser(): User? {
        return client.getCurrentUser()
    }

    sealed class CreateChannelEvent {
        data class Error(val error: String) : CreateChannelEvent()
        object Success : CreateChannelEvent()
    }
}
