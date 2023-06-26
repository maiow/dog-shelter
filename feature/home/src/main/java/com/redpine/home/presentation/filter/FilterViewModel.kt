package com.redpine.home.presentation.filter

import android.content.ContentValues.TAG
import android.util.Log
import com.redpine.core.base.BaseViewModel
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.utils.Filters
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase
) : BaseViewModel() {

    fun onApplyButtonClick(filters: Filters){
        Log.d(TAG, "onApplyButtonClick at FilterViewModel: $filters")
        filterUseCase.setFilters(filters)
    }
//    var minAgeOnSlider = INITIAL_MIN_AGE_ON_SLIDER.toString()
//    var maxAgeOnSlider = INITIAL_MAX_AGE_ON_SLIDER.toString()
//
//    var selectedGenderChip: String? = null
//    var selectedSizeChip: String? = null
//
//    fun filterDogs() {
//        var gender = selectedGenderChip
//        if (selectedGenderChip != GENDER_ANY_RU && selectedGenderChip != GENDER_ANY_EN) {
//            gender =
//                if (selectedGenderChip == GENDER_GIRL_RU || selectedGenderChip == GENDER_GIRL_EN)
//                    FEMALE else MALE
//        }
//        var size = selectedSizeChip
//        if (selectedSizeChip == SIZE_BIG_EN) size = SIZE_BIG_RU
//        if (selectedSizeChip == SIZE_SMALL_EN) size = SIZE_SMALL_RU
//        if (selectedSizeChip == SIZE_MEDIUM_EN) size = SIZE_MEDIUM_RU

//        scopeLaunch {
//            filterUseCase.filterDogs(
//                gender = gender!!, size = size,
//                minAge = minAgeOnSlider, maxAge = maxAgeOnSlider, character = "спокойный"
//            )
//        }
//    }

    var selectedCheckboxes: MutableSet<String>? = mutableSetOf()

    companion object {
        const val INITIAL_MIN_AGE_ON_SLIDER = 3
        const val INITIAL_MAX_AGE_ON_SLIDER = 6
        const val GENDER_ANY_RU = "Любой"
        const val GENDER_ANY_EN = "Any"
        const val GENDER_GIRL_RU = "Девочка"
        const val GENDER_GIRL_EN = "Girl"
        const val MALE = "male"
        const val FEMALE = "female"
        const val SIZE_BIG_EN = "big"
        const val SIZE_SMALL_EN = "small"
        const val SIZE_MEDIUM_EN = "medium"
        const val SIZE_BIG_RU = "крупный"
        const val SIZE_SMALL_RU = "маленький"
        const val SIZE_MEDIUM_RU = "средний"
    }
}