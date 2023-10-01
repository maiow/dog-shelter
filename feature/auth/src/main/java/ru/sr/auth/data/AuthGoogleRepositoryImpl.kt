package ru.sr.auth.data

import android.content.Intent
import android.content.IntentSender
import ru.sr.auth.domain.AuthGoogleRepository
import ru.sr.auth.domain.GoogleAuthApi


class AuthGoogleRepositoryImpl(
    private val authApi: GoogleAuthApi,
) : AuthGoogleRepository {
    override suspend fun signIn(): Result<IntentSender> {
        return try {
            Result.success(authApi.signIn())
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure(exception)
        }
    }

    override suspend fun signWithIntent(intent: Intent): Result<Boolean> {
        return try {
          authApi.signWithIntent(intent)
            Result.success(true)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure(exception)
        }
    }
}