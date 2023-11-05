package com.redpine.auth.di.componentviewmodel

import com.redpine.auth.di.deps.AuthDependencies
import com.redpine.auth.di.deps.AuthDependenciesProvider
import kotlin.properties.Delegates

object AuthStore : AuthDependenciesProvider {

    override var dependencies: AuthDependencies by Delegates.notNull()
}