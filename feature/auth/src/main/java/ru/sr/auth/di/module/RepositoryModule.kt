package ru.sr.auth.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import ru.sr.auth.data.AuthenticationRepositoryImpl
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepositoryImpl(auth: FirebaseAuth) =
        AuthenticationRepositoryImpl(auth)

}
