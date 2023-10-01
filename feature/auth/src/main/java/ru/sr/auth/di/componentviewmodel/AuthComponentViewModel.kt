package ru.sr.auth.di.componentviewmodel

import com.redpine.core.base.ComponentViewModel
import ru.sr.auth.di.component.DaggerAuthComponent
import ru.sr.auth.di.deps.AuthDependenciesProvider

class AuthComponentViewModel : ComponentViewModel() {

    override val moduleComponent = DaggerAuthComponent.builder()
        .dependencies(AuthDependenciesProvider.dependencies)
        .build()
}
