package ru.sr.auth.di.module

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import ru.sr.auth.data.repository.AuthGoogleRepositoryImpl
import ru.sr.auth.data.googleAuth.BeginSignInRequestWrapper
import ru.sr.auth.data.googleAuth.GoogleAuthApiImpl
import ru.sr.auth.data.googleAuth.SignInClientWrapper
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
        beginSignInRequest: BeginSignInRequestWrapper,
    ): GoogleAuthApiImpl {
        return GoogleAuthApiImpl(oneTapClient,beginSignInRequest,Firebase.auth)
    }

    @Provides
    fun providerAuthGoogleRepositoryImpl(authApi: GoogleAuthApi): AuthGoogleRepositoryImpl {
        return AuthGoogleRepositoryImpl(authApi)
    }


}