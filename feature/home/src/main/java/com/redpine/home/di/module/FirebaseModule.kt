package com.redpine.home.di.module

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object FirebaseModule {

    @Provides
    @Singleton
    fun providesFireBase() = Firebase.auth

    @Provides
    fun providesDatabaseReference(): DatabaseReference = Firebase
        .database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference
}