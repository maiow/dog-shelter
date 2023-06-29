package com.redpine.profile.di

import kotlin.properties.Delegates

object ProfileStore : ProfileDependenciesProvider {
    override var dependencies: ProfileDependencies by Delegates.notNull()
}