package com.redpine.core.base

import androidx.lifecycle.ViewModel

abstract class ComponentViewModel: ViewModel(){

    abstract val moduleComponent: DiComponent
}