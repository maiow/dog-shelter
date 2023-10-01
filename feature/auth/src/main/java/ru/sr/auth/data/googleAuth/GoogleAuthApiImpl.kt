package ru.sr.auth.data.googleAuth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.GoogleAuthApi

class GoogleAuthApiImpl(
    private val oneTapClient: SignInClientWrapper,
    private val beginSignInRequestWrapper: BeginSignInRequestWrapper,
    private val auth: FirebaseAuth,
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
                else -> throw UnSupportException()
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
                else -> throw UnSupportException()
            }
        }
    }

}

class NonAuthException : Exception("User not Auth")
class NonGoogleAccountException : Exception("User does not have a google account")
class TimeOutException : Exception("Timeout exceeded")
class UnSupportException : Exception("Something didn't go according to plan")

