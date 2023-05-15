package com.redpine.home.presentation.pets_card

import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.usecase.LikeUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val repository: DogsRepository,
    private val likeUseCase: LikeUseCase
) : BaseViewModel() {

    private val _images = MutableSharedFlow<List<String>>()
    val imagesList = _images.asSharedFlow()

    fun onGettingArgument(dog: Dog) {
        getDogImages(dog.id)
        sendDogToSeenList(dog.id)
    }

    private fun getDogImages(dogId: Int) = scopeLaunch {
        _images.emit(repository.getDogImages(dogId))
    }

    private fun sendDogToSeenList(dogId: Int) = scopeLaunch {
        repository.sendDogToSeenList(dogId)
    }
}