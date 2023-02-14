package com.redpine.home.di

import com.redpine.core.base.ComponentViewModel

class HomeComponentViewModel: ComponentViewModel() {

   override val moduleComponent = DaggerHomeComponent
        .builder()
        .dependencies(HomeDependenciesProvider.dependencies)
        .build()

}
