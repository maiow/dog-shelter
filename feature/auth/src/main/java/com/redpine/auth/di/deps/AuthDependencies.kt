package com.redpine.auth.di.deps

import android.content.Context
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.TokenProvider

interface AuthDependencies {
    val tokenProvider: TokenProvider
    val context:Context
    val authDialogPrefs: AuthDialogPrefs
}