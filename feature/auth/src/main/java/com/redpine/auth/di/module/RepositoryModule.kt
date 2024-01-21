package com.redpine.auth.di.module

import com.google.firebase.auth.FirebaseAuth
import com.redpine.auth.data.repository.AuthenticationEmailAndPasswordRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepositoryImpl(auth: FirebaseAuth) =
        AuthenticationEmailAndPasswordRepositoryImpl(auth)

}
