package com.redpine.home.di.deps

import com.redpine.home.di.componentviewmodel.HomeStore

interface HomeDependenciesProvider {
    var dependencies: HomeDependencies

    companion object : HomeDependenciesProvider by HomeStore
}