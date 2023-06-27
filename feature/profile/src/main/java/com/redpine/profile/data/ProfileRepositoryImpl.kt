package com.redpine.profile.data

import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.redpine.profile.domain.ProfileRepository
import kotlinx.coroutines.tasks.await

class ProfileRepositoryImpl : ProfileRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun isUserAuthorized(): Boolean = getUserId() != null

    override suspend fun getEmail(): String = firebaseAuth.currentUser?.email ?: ""

    override suspend fun reauthenticateUser(password: String): Boolean {
        val user = firebaseAuth.currentUser ?: return false
        val email = getEmail()
        try {
            val credential = EmailAuthProvider
                .getCredential(email, password)
            user.reauthenticate(credential).await()
            return true
        } catch (e: Exception) {
            Log.e("BRED", e.localizedMessage ?: "")
        }
        return false
    }

    override suspend fun deleteAccount(): Boolean {
        val user = firebaseAuth.currentUser ?: return false

        try {
            user.delete().await()
            return true
        } catch (e: Exception) {
            Log.e("BRED", e.localizedMessage ?: "")
        }
        return false
    }

    override suspend fun logout(): Boolean {
        try {
            firebaseAuth.signOut()
            return true
        } catch (e: Exception) {
            Log.e("BRED", e.localizedMessage ?: "")
        }
        return false
    }
}