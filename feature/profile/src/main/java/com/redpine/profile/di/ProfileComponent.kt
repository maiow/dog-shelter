package com.redpine.profile.di

import androidx.lifecycle.ViewModelProvider
import com.redpine.core.base.DiComponent
import com.redpine.profile.di.module.Binds
import com.redpine.profile.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        ProfileDependencies::class
    ], modules = [
        RepositoryModule::class,
        Binds::class
    ]
)
@Singleton
interface ProfileComponent : DiComponent {
    override val viewModelFactory: ViewModelProvider.Factory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ProfileDependencies): Builder

        fun build(): ProfileComponent
    }
}