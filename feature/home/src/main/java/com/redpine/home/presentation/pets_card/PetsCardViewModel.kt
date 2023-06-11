package com.redpine.home.presentation.pets_card

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.LikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val repository: DogsRepository,
    private val likeUseCase: LikeUseCase
) : BaseViewModel() {

    private val _images = MutableSharedFlow<List<String>>()
    val imagesList = _images.asSharedFlow()

    private val _dogInfo = MutableSharedFlow<Dog>()
    val dogInfo = _dogInfo.asSharedFlow()

    fun onGettingArgument(dog: Dog) {
        getDogInfo(dog)
        getDogImages(dog.id)
        sendDogToSeenList(dog.id)
    }

    private fun getDogInfo(dog:Dog){
        scopeLaunch {
            _dogInfo.emit(repository.getDogInfo(dog.id).copy(isFavorite = dog.isFavorite))
        }
    }

    private fun getDogImages(dogId: Int) = scopeLaunch {
        _images.emit(repository.getDogImages(dogId))
    }

    private fun sendDogToSeenList(dogId: Int) = scopeLaunch {
        repository.sendDogToSeenList(dogId)
    }

    fun addToFavorites(dog: Dog){
        viewModelScope.launch(Dispatchers.IO) {
            if (likeUseCase.makeLikeDislike(dog.id, !dog.isFavorite))
                _dogInfo.emit(dog.copy(isFavorite = !dog.isFavorite))
        }
    }
}