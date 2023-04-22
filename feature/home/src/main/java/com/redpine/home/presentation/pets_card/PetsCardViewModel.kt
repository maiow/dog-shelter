package com.redpine.home.presentation.pets_card

import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.Response
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val repository: DogsRepository
) : BaseViewModel() {

    private val _data = MutableSharedFlow<List<String>>()
    val data = _data.asSharedFlow()

    /** убрать при настройке взаимодействия с бэком */
    fun getCuratorNumber(dogId: Int) = "+79167777777"

    fun getDogLink(dogId: Int) = "1-848899393821-valli"

    fun getDogName(dogId: Int) = "Викки"

    fun getDogImages(dogId: Int) = scopeLaunch {
        _data.emit(repository.getDogImages(dogId))
    }
}