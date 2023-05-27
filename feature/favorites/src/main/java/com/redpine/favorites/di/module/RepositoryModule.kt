package com.redpine.favorites.di.module

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.redpine.favorites.data.FavoritesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesFavoritesRepository(database: DatabaseReference) = FavoritesRepositoryImpl(database)

    @Provides
    fun providesDatabaseReference(): DatabaseReference = Firebase
        .database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference
}