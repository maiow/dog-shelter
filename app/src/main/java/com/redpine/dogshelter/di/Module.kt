package com.redpine.dogshelter.di

import com.redpine.api.Api
import dagger.Provides
import javax.inject.Singleton

@dagger.Module
object Module {

    @Provides
    @Singleton
    fun providesApi() = Api()
}