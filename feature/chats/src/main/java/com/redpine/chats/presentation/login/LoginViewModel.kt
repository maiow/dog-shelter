package com.redpine.chats.presentation.login

import androidx.lifecycle.viewModelScope
import com.redpine.chats.util.Constants
import com.redpine.core.base.BaseViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.client.models.User
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel @Inject constructor(
    private val client: ChatClient
) : BaseViewModel() {

    private val _loginEvent = MutableSharedFlow<LogInEvent>()
    val loginEvent = _loginEvent.asSharedFlow()

    internal fun checkIfAlreadyLogged() {
        //  if (client.getCurrentUser()?.id == )
        //  _loginEvent.emit(LogInEvent.Success)
    }

    private fun isValidUsername(username: String) =
        username.length >= Constants.MIN_USERNAME_LENGTH

    internal fun connectUser(username: String) {
        val trimmedUsername = username.trim()
        viewModelScope.launch {
            //проверка валидности и законнектился ли уже раньше:
            if (isValidUsername(trimmedUsername) && (client.getCurrentUser()?.id != trimmedUsername)) {
                //для входа в чат только тех, кто зарегался и получил токен:
                val result = client.connectUser(
                    User(
                        id = trimmedUsername,
                        name = trimmedUsername,
//                        image =
//                            "https://getstream.io/random_png?id=${trimmedUsername}&name=${trimmedUsername}&size=200"
                    ),
                    client.devToken(trimmedUsername)
                ).await()

                //для входа в чат всех без получения токенов:
//                val result =
//                    client.connectGuestUser(userId = trimmedUsername, username = username).await()
                if (result.isError) {
                    _loginEvent.emit(
                        LogInEvent.ErrorLogIn(
                            result.error().message ?: "Unknown error"
                        )
                    )
                    return@launch
                }
                //добавить юзера в канал volunteers
                val channelClient = client.channel("messaging", "volunteers")
                channelClient.addMembers(
                    listOf(trimmedUsername),
                    //TODO: fix
                    // This is internal API for the Stream Chat libraries. Do not depend on this API in your own client code.
                    Message(text = "$trimmedUsername вошёл в чат.")
                ).enqueue { result ->
                    if (result.isSuccess) {
                        val channel: Channel = result.data()
                    } else {
                        // Handle result.error()
                    }
                }
                _loginEvent.emit(LogInEvent.Success)
            } else {
                _loginEvent.emit(LogInEvent.ErrorInputTooShort)
            }
        }
    }

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

    sealed class LogInEvent {
        object ErrorInputTooShort : LogInEvent()
        data class ErrorLogIn(val error: String) : LogInEvent()
        object Success : LogInEvent()
    }
}
