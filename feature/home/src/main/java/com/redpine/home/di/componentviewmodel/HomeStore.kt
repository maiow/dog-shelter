package com.redpine.home.di.componentviewmodel

import com.redpine.home.di.deps.HomeDependencies
import com.redpine.home.di.deps.HomeDependenciesProvider
import kotlin.properties.Delegates

object HomeStore : HomeDependenciesProvider {
    override var dependencies: HomeDependencies by Delegates.notNull()
}