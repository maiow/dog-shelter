package com.redpine.home.di.component

import androidx.lifecycle.ViewModelProvider
import com.redpine.core.base.DiComponent
import com.redpine.home.di.deps.HomeDependencies
import com.redpine.home.di.module.Binds
import com.redpine.home.di.module.FirebaseModule
import com.redpine.home.di.module.RepositoryModule
import com.redpine.home.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        HomeDependencies::class
    ], modules = [
        RepositoryModule::class,
        Binds::class,
        FirebaseModule::class,
        UseCaseModule::class
    ]
)
@Singleton
interface HomeComponent : DiComponent {
    override val viewModelFactory: ViewModelProvider.Factory


    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: HomeDependencies): Builder

        fun build(): HomeComponent
    }
}