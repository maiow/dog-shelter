package com.redpine.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository

class AuthenticationEmailAndPasswordRepositoryImpl(
    private val fireBaseAuth: FirebaseAuth
) : AuthenticationEmailAndPasswordRepository {

    override fun authEmail(email: String, password: String) =
        fireBaseAuth.signInWithEmailAndPassword(email, password)

    override fun createUser(email: String, password: String) =
        fireBaseAuth.createUserWithEmailAndPassword(email, password)

    override fun resetPassword(email: String) =
        fireBaseAuth.sendPasswordResetEmail(email)
}