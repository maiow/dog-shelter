package com.redpine.home.presentation.filter

import android.util.Log
import com.redpine.core.base.BaseViewModel
import com.redpine.home.domain.usecase.FilterUseCase
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase
) : BaseViewModel() {

    var minAgeOnSlider = INITIAL_MIN_AGE_ON_SLIDER.toString()
    var maxAgeOnSlider = INITIAL_MAX_AGE_ON_SLIDER.toString()

    var selectedGenderChip: String? = null
    var selectedSizeChip: String? = null

    fun filterDogs() {
        Log.i("BRED", "selectedGenderChip: $selectedGenderChip")
        Log.i("BRED", "selectedSizeChip: $selectedSizeChip")
        var gender = selectedGenderChip
        if (selectedGenderChip != "Любой" && selectedGenderChip != "Any") {
            gender =
                if (selectedGenderChip == "Девочка" || selectedGenderChip == "Girl") "female" else "male"
        }

        scopeLaunch {
            filterUseCase.filterDogs(
                gender = gender!!, size = selectedSizeChip,
                minAge = minAgeOnSlider, maxAge = maxAgeOnSlider, character = "спокойный"
            )
        }
    }

    var selectedCheckboxes: MutableSet<String>? = mutableSetOf()

    companion object {
        const val INITIAL_MIN_AGE_ON_SLIDER = 3
        const val INITIAL_MAX_AGE_ON_SLIDER = 6
    }
}