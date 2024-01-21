package com.redpine.auth.di.componentviewmodel

import com.redpine.auth.di.deps.AuthDependenciesProvider
import com.redpine.core.base.ComponentViewModel
import com.redpine.auth.di.component.DaggerAuthComponent

class AuthComponentViewModel : ComponentViewModel() {

    override val moduleComponent = DaggerAuthComponent.builder()
        .dependencies(AuthDependenciesProvider.dependencies)
        .build()
}
