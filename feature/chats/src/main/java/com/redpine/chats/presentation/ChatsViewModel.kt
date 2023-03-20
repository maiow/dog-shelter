package com.redpine.chats.presentation

import com.redpine.api.Api
import com.redpine.chats.domain.ChatsRepository
import com.redpine.core.base.BaseViewModel
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val api: Api,
    private val chatsRepository: ChatsRepository
) : BaseViewModel() {

    internal fun getText() =
        //{
       // viewModelScope.launch(Dispatchers.Main + handler) {
       //     _loadState.value = LoadState.LOADING
            api.getInfo()
       //     _loadState.value = LoadState.SUCCESS
      //  }
   // }

    internal fun getMessages() = chatsRepository.getInfo()

}