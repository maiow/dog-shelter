package com.redpine.home.data

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.redpine.home.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {

    override fun authEmail(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password)

    override fun getUser() = auth.currentUser

    override fun createUser(email: String, password: String): Task<AuthResult> {

        val test = auth.createUserWithEmailAndPassword(email, password)
        val user = auth.currentUser
        if (user != null && !user.isEmailVerified)
            user.sendEmailVerification(ActionCodeSettings.zzb()).addOnCompleteListener {
                Log.e("Kart", "very = ${it.isSuccessful}")
            }
        return test
    }

    override fun userEmailVerified() =
        auth.currentUser?.isEmailVerified ?: false

}