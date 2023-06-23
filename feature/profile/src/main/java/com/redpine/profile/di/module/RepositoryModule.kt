package com.redpine.profile.di.module

import com.redpine.profile.data.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesProfileRepository() = ProfileRepositoryImpl()

}