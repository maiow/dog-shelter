package ru.sr.auth.di.component

import androidx.lifecycle.ViewModelProvider
import com.redpine.core.base.DiComponent
import dagger.Component
import ru.sr.auth.di.deps.AuthDependencies
import ru.sr.auth.di.module.Binds
import ru.sr.auth.di.module.FirebaseModule
import ru.sr.auth.di.module.GoogleAuthModule
import ru.sr.auth.di.module.RepositoryModule
import ru.sr.auth.di.module.UseCaseModule
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