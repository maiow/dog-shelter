package com.redpine.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.api.Api
import com.redpine.core.base.BaseViewModel
import com.redpine.core.state.LoadState
import com.redpine.favorites.domain.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val api: Api,
    private val favoritesRepository: FavoritesRepository
) : BaseViewModel() {

    internal fun getText() =
        //{
       // viewModelScope.launch(Dispatchers.Main + handler) {
       //     _loadState.value = LoadState.LOADING
            api.getInfo()
       //     _loadState.value = LoadState.SUCCESS
      //  }
   // }

    internal fun getDogInfo() = favoritesRepository.getInfo()[0]

}