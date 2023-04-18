package com.redpine.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.redpine.core.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val _loadState = MutableStateFlow(LoadState.START)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, e ->
        Log.e("Kart", "${e.message} ${e}")
        _loadState.value = if (e is FirebaseAuthInvalidCredentialsException)
            LoadState.ERROR_AUTH
        else LoadState.ERROR_NETWORK
    }

    protected fun scopeAction(
        context: CoroutineContext = Dispatchers.IO + handler,
        startLoadingState: LoadState = LoadState.LOADING,
        endLoadingState: LoadState = LoadState.SUCCESS,
        block: suspend () -> Unit,
    ) {
        viewModelScope.launch(context) {
            _loadState.value = startLoadingState
            block()
            _loadState.value = endLoadingState
        }
    }
}
