package com.redpine.home.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @Provides
    fun providesOnBoardingRepository() = FirebaseAuth.getInstance()
}