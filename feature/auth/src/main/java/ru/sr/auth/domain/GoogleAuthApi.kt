package ru.sr.auth.domain

import android.content.Intent
import android.content.IntentSender
import com.google.firebase.auth.FirebaseUser

interface GoogleAuthApi {
    suspend fun signIn(): IntentSender
    suspend fun signWithIntent(intent: Intent):FirebaseUser
}