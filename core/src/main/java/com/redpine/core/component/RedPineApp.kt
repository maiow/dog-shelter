package com.redpine.core.component

import android.content.Context

interface RedPineApp {

    val appComponent:RedPineComponent

}
interface RedPineComponent

@Suppress("UNCHECKED_CAST")
fun <T:RedPineComponent>Context.getComponent(): T {
  return  when (this) {
        is RedPineApp -> appComponent as T
        else -> this.applicationContext.getComponent()
    }
}