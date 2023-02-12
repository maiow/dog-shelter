package com.redpine.home.di

import kotlin.properties.Delegates

object HomeStore : HomeDependenciesProvider {
    override var dependencies: HomeDependencies by Delegates.notNull()
}