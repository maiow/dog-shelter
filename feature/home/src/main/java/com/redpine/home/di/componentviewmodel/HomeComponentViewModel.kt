package com.redpine.home.di.componentviewmodel

import com.redpine.core.base.ComponentViewModel
import com.redpine.home.di.component.DaggerHomeComponent
import com.redpine.home.di.deps.HomeDependenciesProvider

class HomeComponentViewModel : ComponentViewModel() {

    override val moduleComponent = DaggerHomeComponent.builder()
        .dependencies(HomeDependenciesProvider.dependencies)
        .build()
}
