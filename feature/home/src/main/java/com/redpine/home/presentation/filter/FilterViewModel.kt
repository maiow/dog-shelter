package com.redpine.home.presentation.filter

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    internal fun getText() = api.getInfo()

}