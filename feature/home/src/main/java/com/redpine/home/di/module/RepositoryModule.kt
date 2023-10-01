package com.redpine.home.di.module

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.redpine.home.data.repository.DogsRepositoryImpl
import com.redpine.home.data.repository.FiltrationRepositoryImpl
import com.redpine.home.data.repository.NewsRepositoryImpl
import com.redpine.home.data.repository.OnboardingRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOnboardingRepository() = OnboardingRepositoryImpl()


    @Provides
    @Singleton
    fun provideNewsRepository(database: DatabaseReference) = NewsRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideDogsRepository(database: DatabaseReference) = DogsRepositoryImpl(database, Firebase.auth)

    @Provides
    fun providesDatabaseReference(): DatabaseReference = Firebase
        .database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference

    @Provides
    @Singleton
    fun providesFiltrationRepository() = FiltrationRepositoryImpl()
}
