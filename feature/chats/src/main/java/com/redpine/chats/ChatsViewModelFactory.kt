package com.redpine.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import com.redpine.chats.domain.ChatsRepository
import com.redpine.chats.presentation.ChatsViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ChatsViewModelFactory @Inject constructor(
    private val api: Api,
    private val chatsRepository: ChatsRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ChatsViewModel::class.java -> ChatsViewModel(api, chatsRepository) as T
        //FavoritesSecondViewModel::class.java -> OnboardingViewModel(onboardingRepository) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}


