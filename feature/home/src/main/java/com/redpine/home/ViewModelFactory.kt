package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.api.Api
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val api: Api) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == HomeViewModel::class.java)
        return HomeViewModel(api) as T

    }

}