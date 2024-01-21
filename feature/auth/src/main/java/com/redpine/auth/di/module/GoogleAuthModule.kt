package com.redpine.auth.di.module

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.redpine.auth.data.googleAuth.BeginSignInRequestWrapper
import com.redpine.auth.data.googleAuth.GoogleAuthApiImpl
import com.redpine.auth.data.googleAuth.SignInClientWrapper
import com.redpine.auth.data.repository.AuthGoogleRepositoryImpl
import com.redpine.auth.domain.GoogleAuthApi
import dagger.Module
import dagger.Provides

@Module
class GoogleAuthModule {

    @Provides
    fun providerBeginSignInRequest(): BeginSignInRequestWrapper {
        return BeginSignInRequestWrapper()
    }

    @Provides
    fun providerSignInClientWrapper(context: Context): SignInClientWrapper {
        return SignInClientWrapper(context)
    }

    @Provides
    fun providerGoogleAuthApiImpl(
        oneTapClient: SignInClientWrapper,
        beginSignInRequest: BeginSignInRequestWrapper,
    ): GoogleAuthApiImpl {
        return GoogleAuthApiImpl(oneTapClient, beginSignInRequest, Firebase.auth)
    }

    @Provides
    fun providerAuthGoogleRepositoryImpl(authApi: GoogleAuthApi): AuthGoogleRepositoryImpl {
        return AuthGoogleRepositoryImpl(authApi)
    }

}