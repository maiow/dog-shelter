package com.redpine.core.base

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel

abstract class ComponentViewModel: ViewModel(){

    init {
        Log.d(TAG, "------------\n$this: ")
    }

    abstract val moduleComponent: DiComponent

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: $this")
    }

}