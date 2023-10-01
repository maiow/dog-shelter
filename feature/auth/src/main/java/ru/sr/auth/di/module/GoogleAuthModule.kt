package ru.sr.auth.di.module

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.Builder
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import ru.sr.auth.data.AuthGoogleRepositoryImpl
import ru.sr.auth.data.BeginSignInRequestWrapper
import ru.sr.auth.data.GoogleAuthApiImpl
import ru.sr.auth.data.SignInClientWrapper
import ru.sr.auth.domain.GoogleAuthApi

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
  //      beginSignInRequest: BeginSignInRequestWrapper,
    ): GoogleAuthApiImpl {
        return GoogleAuthApiImpl(oneTapClient)
    }

    @Provides
    fun providerAuthGoogleRepositoryImpl(authApi: GoogleAuthApi): AuthGoogleRepositoryImpl {
        return AuthGoogleRepositoryImpl(authApi)
    }


}