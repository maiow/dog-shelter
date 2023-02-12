package com.redpine.home.di

interface HomeDependenciesProvider {
    var dependencies:HomeDependencies

    companion object: HomeDependenciesProvider by HomeStore
}