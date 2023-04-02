package com.redpine.home.presentation.filter

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import com.redpine.home.INITIAL_MAX_AGE_ON_SLIDER
import com.redpine.home.INITIAL_MIN_AGE_ON_SLIDER
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    //fun getText() = api.getInfo()

    var minAgeOnSlider = INITIAL_MIN_AGE_ON_SLIDER.toString()
    var maxAgeOnSlider = INITIAL_MAX_AGE_ON_SLIDER.toString()

}