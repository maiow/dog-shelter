package com.redpine.core.base

import androidx.lifecycle.ViewModelProvider

interface DiComponent{
    val viewModelFactory: ViewModelProvider.Factory
}