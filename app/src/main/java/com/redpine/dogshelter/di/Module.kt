package com.redpine.dogshelter.di

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.redpine.api.Api
import com.redpine.core.data.TokenProviderImpl
import com.redpine.core.domain.TokenProvider
import dagger.Provides
import javax.inject.Singleton

@dagger.Module
object Module {

    @Provides
    @Singleton
    fun providesApi() = Api()

    @Provides
    @Singleton
    fun providesTokenProvider(context: Context): TokenProvider = TokenProviderImpl(context)


}

