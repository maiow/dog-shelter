package ru.sr.auth.presentation.state

import android.content.IntentSender

data class AuthState(
    val intentSender: IntentSender? = null
)