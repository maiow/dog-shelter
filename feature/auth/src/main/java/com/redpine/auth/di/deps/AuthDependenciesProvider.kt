package com.redpine.auth.di.deps

import com.redpine.auth.di.componentviewmodel.AuthStore

interface AuthDependenciesProvider {
    var dependencies: AuthDependencies

    companion object : AuthDependenciesProvider by AuthStore
}