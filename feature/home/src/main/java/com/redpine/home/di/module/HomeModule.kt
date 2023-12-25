package com.redpine.home.di.module

import dagger.Module

@Module(
    includes = [
        Binds::class,
        FirebaseModule::class,
    ]
)
class HomeModule