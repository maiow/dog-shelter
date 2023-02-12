package com.redpine.home

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    fun getText() = api.getInfo()


}