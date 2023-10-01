package ru.sr.auth.data

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient


class SignInClientWrapper(context: Context) {
    val client: SignInClient = Identity.getSignInClient(context)
}

class BeginSignInRequestWrapper{

    val beginSignInRequest = BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("8910379865-hm1cba7du6jeei48odt44sms0m2vfa9n.apps.googleusercontent.com")
            .build()
    ).setAutoSelectEnabled(true)
        .build()
}