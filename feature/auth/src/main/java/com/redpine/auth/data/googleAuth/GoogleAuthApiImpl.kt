package com.redpine.auth.data.googleAuth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.redpine.auth.domain.GoogleAuthApi
import kotlinx.coroutines.tasks.await

class GoogleAuthApiImpl(
    private val oneTapClient: SignInClientWrapper,
    private val beginSignInRequestWrapper: BeginSignInRequestWrapper,
    private val auth: FirebaseAuth
) : GoogleAuthApi {


    override suspend fun signIn(): IntentSender {
        return try {
            oneTapClient.client
                .beginSignIn(beginSignInRequestWrapper.beginSignInRequest)
                .await().pendingIntent.intentSender
        } catch (e: ApiException) {

            when (e.status) {
                Status.RESULT_CANCELED -> throw NonGoogleAccountException()
                Status.RESULT_DEAD_CLIENT -> throw NonGoogleAccountException()
                Status.RESULT_TIMEOUT -> throw TimeOutException()
                else -> throw UnsupportedException()
            }
        }
    }

    override suspend fun signWithIntent(intent: Intent): FirebaseUser {
        val credential = oneTapClient.client.getSignInCredentialFromIntent(intent)
        val googleToken = credential.googleIdToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleToken, null)

        return try {
            auth.signInWithCredential(googleAuthCredential)
                .await()
                .user
                ?: throw NonAuthException()

        } catch (e: ApiException) {
            when (e.status) {
                Status.RESULT_CANCELED -> throw NonGoogleAccountException()

                Status.RESULT_DEAD_CLIENT -> throw NonGoogleAccountException()
                Status.RESULT_TIMEOUT -> throw TimeOutException()
                else -> throw UnsupportedException()
            }
        }
    }

}

class NonAuthException : Exception("User not Auth")
class NonGoogleAccountException : Exception("User does not have a google account")
class TimeOutException : Exception("Timeout exceeded")
class UnsupportedException : Exception("Something didn't go according to plan")

