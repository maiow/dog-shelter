package ru.sr.auth.data

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.sr.auth.domain.GoogleAuthApi

class GoogleAuthApiImpl(
    private val oneTapClient: SignInClientWrapper,
  //  private val beginSignInRequestWrapper: BeginSignInRequestWrapper,
    // private val auth: FirebaseAuth ,
) : GoogleAuthApi {

  val auth =    Firebase.auth

    override suspend fun signIn(): IntentSender {
        return try {
            val a = BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("8910379865-hm1cba7du6jeei48odt44sms0m2vfa9n.apps.googleusercontent.com")
                    .build()
            ).setAutoSelectEnabled(true)
                .build()
            oneTapClient.client
                .beginSignIn(a)
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

    override suspend fun signWithIntent(intent: Intent){
        val credential = oneTapClient.client.getSignInCredentialFromIntent(intent)
        val googleToken = credential.googleIdToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleToken, null)

        return try {
            val user = auth.signInWithCredential(googleAuthCredential)
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

