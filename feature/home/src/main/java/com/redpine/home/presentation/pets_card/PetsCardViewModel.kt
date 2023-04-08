package com.redpine.home.presentation.pets_card

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import javax.inject.Inject

class PetsCardViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    /** убрать при настройке взаимодействия с бэком */
    fun getCuratorNumber(dogId: Int) = "+79167777777"

    fun getDogLink(dogId: Int) = "1-848899393821-valli"
}