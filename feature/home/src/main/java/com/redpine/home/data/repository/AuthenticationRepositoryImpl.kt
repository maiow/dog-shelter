package com.redpine.home.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.redpine.home.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(private val fireBaseAuth:FirebaseAuth): AuthenticationRepository {

    override fun authEmail(email: String, password: String) =
        fireBaseAuth.signInWithEmailAndPassword(email, password)

    override fun getUser() = fireBaseAuth.currentUser

    override fun createUser(email: String, password: String): Task<AuthResult> {

        val test = fireBaseAuth.createUserWithEmailAndPassword(email, password)
        val user = fireBaseAuth.currentUser
        if (user != null && !user.isEmailVerified)
            user.sendEmailVerification(ActionCodeSettings.zzb()).addOnCompleteListener {
                Log.e("Kart", "very = ${it.isSuccessful}")
            }
        return test
    }

    override fun userEmailVerified() =
        fireBaseAuth.currentUser?.isEmailVerified ?: false
}