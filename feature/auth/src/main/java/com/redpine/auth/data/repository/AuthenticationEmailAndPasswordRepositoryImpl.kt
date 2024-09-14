package com.redpine.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import kotlinx.coroutines.tasks.await

class AuthenticationEmailAndPasswordRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationEmailAndPasswordRepository {

    override fun authEmail(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    override fun createUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    override fun resetPassword(email: String) =
        firebaseAuth.sendPasswordResetEmail(email)

    override suspend fun checkIfNewUser(email: String): Boolean =
        firebaseAuth.fetchSignInMethodsForEmail(email).await().signInMethods.isNullOrEmpty()
}
