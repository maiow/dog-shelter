package com.redpine.home.presentation.filter

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    //TODO: отправка собранных данных на сервер

    //fun sendInfo() = api.sendInfo()

    var minAgeOnSlider = INITIAL_MIN_AGE_ON_SLIDER.toString()
    var maxAgeOnSlider = INITIAL_MAX_AGE_ON_SLIDER.toString()

    var selectedGenderChip: String? = null
    var selectedSizeChip: String? = null

    /** отправляем на сервер список string выбранных чекбоксов
     * или пустой список, если ничего не отмечено
     * со string будет меньше ошибок при внесении изменений в чекбоксы, чем если перегонять в int*/

    var selectedCheckboxes: MutableSet<String>? = mutableSetOf()

    /**пока что убрал сюда. можно убрать куда-нибудь еще)*/
    companion object{
        const val INITIAL_MIN_AGE_ON_SLIDER = 3
        const val INITIAL_MAX_AGE_ON_SLIDER = 6
    }
}