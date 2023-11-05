package com.redpine.auth.data.googleAuth

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient

class SignInClientWrapper(context: Context) {
    val client: SignInClient = Identity.getSignInClient(context)
}

