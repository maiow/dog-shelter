package com.redpine.chats.di

import androidx.lifecycle.ViewModelProvider
import com.redpine.chats.di.module.Binds
import com.redpine.core.base.DiComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        ChatsDependencies::class
    ],
    modules = [
        Binds::class
    ]
)
@Singleton
interface ChatsComponent : DiComponent {
    override val viewModelFactory: ViewModelProvider.Factory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ChatsDependencies): Builder

        fun build(): ChatsComponent
    }
}
