package com.redpine.profile.di

import com.redpine.core.base.ComponentViewModel

class ProfileComponentViewModel: ComponentViewModel() {

   override val moduleComponent = DaggerProfileComponent
        .builder()
        .dependencies(ProfileDependenciesProvider.dependencies)
        .build()

}
