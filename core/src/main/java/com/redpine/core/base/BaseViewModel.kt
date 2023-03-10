package com.redpine.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.redpine.core.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loadState = MutableStateFlow(LoadState.LOADING)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, e ->
        Log.e("RedPi","${e.message} $e")
        _loadState.value = LoadState.ERROR
    }
}
