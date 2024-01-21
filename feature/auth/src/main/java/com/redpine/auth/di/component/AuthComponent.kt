package com.redpine.auth.di.component

import androidx.lifecycle.ViewModelProvider
import com.redpine.auth.di.deps.AuthDependencies
import com.redpine.auth.di.module.Binds
import com.redpine.auth.di.module.FirebaseModule
import com.redpine.auth.di.module.GoogleAuthModule
import com.redpine.auth.di.module.RepositoryModule
import com.redpine.auth.di.module.UseCaseModule
import com.redpine.core.base.DiComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        AuthDependencies::class
    ], modules = [
        RepositoryModule::class,
        GoogleAuthModule::class,
        Binds::class,
        FirebaseModule::class,
        UseCaseModule::class
    ]
)
@Singleton
interface AuthComponent : DiComponent {
    override val viewModelFactory: ViewModelProvider.Factory


    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: AuthDependencies): Builder

        fun build(): AuthComponent
    }
}