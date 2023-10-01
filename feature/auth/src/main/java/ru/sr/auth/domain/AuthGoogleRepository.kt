package ru.sr.auth.domain

import android.content.Intent
import android.content.IntentSender

interface AuthGoogleRepository {
    suspend fun signIn(): Result<IntentSender>
    suspend fun signWithIntent(intent: Intent): Result<String>
}
