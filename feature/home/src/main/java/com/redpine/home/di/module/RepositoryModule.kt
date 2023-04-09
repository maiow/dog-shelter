package com.redpine.home.di.module

import com.google.firebase.auth.FirebaseAuth
import com.redpine.home.data.repository.AuthenticationRepositoryImpl
import com.redpine.home.data.repository.OnboardingRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOnBoardingRepository() = OnboardingRepositoryImpl()

    @Provides
    @Singleton
    fun providesAuthenticationRepository(auth:FirebaseAuth) = AuthenticationRepositoryImpl(auth)
}
