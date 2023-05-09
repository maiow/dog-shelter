package com.redpine.home.presentation.pets_card

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.Dog
import com.redpine.home.domain.repository.DogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val repository: DogsRepository
) : BaseViewModel() {

    private val _images = MutableSharedFlow<List<String>>()
    val imagesList = _images.asSharedFlow()

    private val _dogInfoState = MutableStateFlow<State>(State.Loading)
    val dogInfoState = _dogInfoState.asStateFlow()

    fun onGettingArgument(dogId: Int) {
        getDogInfo(dogId)
    }

    fun getDogLink(dogId: Int) = "1-848899393821-valli"

    private fun getDogInfo(dogId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _dogInfoState.value = State.Loading
                _dogInfoState.value = State.Loaded(repository.getDogInfo(dogId))
                getDogImages(dogId)
            } catch (e: Exception) {
                _dogInfoState.value = State.Error(e.message ?: "Unknown error")
            }
        }

    private fun getDogImages(dogId: Int) = scopeLaunch {
        _images.emit(repository.getDogImages(dogId))
    }

    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State

        data class Loaded(
            val dogInfo: Dog
        ) : State
    }
}