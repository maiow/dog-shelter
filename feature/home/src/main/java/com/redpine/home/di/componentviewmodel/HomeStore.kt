package com.redpine.home.di.componentviewmodel

import android.content.ContentValues
import android.util.Log
import com.redpine.home.di.deps.HomeDependencies
import com.redpine.home.di.deps.HomeDependenciesProvider
import kotlin.properties.Delegates

object HomeStore : HomeDependenciesProvider {


    override var dependencies: HomeDependencies by Delegates.notNull()

    init {
        Log.d(ContentValues.TAG, "homeStore: ${Delegates.notNull<HomeDependencies>()}")
    }
}