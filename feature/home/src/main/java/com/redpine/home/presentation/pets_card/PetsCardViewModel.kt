package com.redpine.home.presentation.pets_card

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.usecase.DogInfoUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import com.redpine.home.domain.usecase.SeenListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val dogInfoUseCase: DogInfoUseCase,
    private val seenListUseCase: SeenListUseCase,
    private val likeUseCase: LikeUseCase,
) : BaseViewModel() {

    private val _images = MutableSharedFlow<List<String>>()
    val imagesList = _images.asSharedFlow()

    private val _dogInfo = MutableSharedFlow<Dog>()
    val dogInfo = _dogInfo.asSharedFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    fun onGettingArgument(dog: Dog) {
        getDogInfo(dog)
        getDogImages(dog.id)
        sendDogToSeenList(dog.id)
    }

    private fun getDogInfo(dog:Dog) = scopeLaunch {
        _dogInfo.emit(dogInfoUseCase.getDogInfo(dog.id).copy(isFavorite = dog.isFavorite))
    }

    private fun getDogImages(dogId: Int) = scopeLaunch {
        _images.emit(dogInfoUseCase.getDogImages(dogId))
    }

    private fun sendDogToSeenList(dogId: Int) = scopeLaunch {
        seenListUseCase.sendDogToSeenList(dogId)
    }

    fun addToFavorites(dog: Dog){
        viewModelScope.launch(Dispatchers.IO) {
            if (likeUseCase.makeLikeDislike(dog.id, !dog.isFavorite))
                _dogInfo.emit(dog.copy(isFavorite = !dog.isFavorite))
            else _isNavigateAuth.value = true
        }
    }

    fun resetNavigateFlow() {
        _isNavigateAuth.value = false
    }
}