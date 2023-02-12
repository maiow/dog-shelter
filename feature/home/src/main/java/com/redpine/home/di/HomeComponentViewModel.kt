package com.redpine.home.di

import androidx.lifecycle.ViewModel

class HomeComponentViewModel: ViewModel() {

    val homeComponent = DaggerHomeComponent
        .builder()
        .dependencies(HomeDependenciesProvider.dependencies)
        .build()

}