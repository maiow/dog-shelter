package ru.sr.auth.di.deps

import ru.sr.auth.di.componentviewmodel.AuthStore

interface AuthDependenciesProvider {
    var dependencies: AuthDependencies

    companion object : AuthDependenciesProvider by AuthStore
}