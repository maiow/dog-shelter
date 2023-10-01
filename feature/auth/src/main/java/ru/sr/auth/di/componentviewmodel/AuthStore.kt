package ru.sr.auth.di.componentviewmodel

import ru.sr.auth.di.deps.AuthDependencies
import ru.sr.auth.di.deps.AuthDependenciesProvider
import kotlin.properties.Delegates

object AuthStore : AuthDependenciesProvider {

    override var dependencies: AuthDependencies by Delegates.notNull()
}