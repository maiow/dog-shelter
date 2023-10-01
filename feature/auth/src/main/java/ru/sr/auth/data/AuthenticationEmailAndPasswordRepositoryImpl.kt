package ru.sr.auth.data

import com.google.firebase.auth.FirebaseAuth
import ru.sr.auth.domain.AuthenticationEmailAndPasswordRepository

class AuthenticationEmailAndPasswordRepositoryImpl(
    private val fireBaseAuth: FirebaseAuth,
) : AuthenticationEmailAndPasswordRepository {

    override fun authEmail(email: String, password: String) =
        fireBaseAuth.signInWithEmailAndPassword(email, password)

    override fun createUser(email: String, password: String) =
        fireBaseAuth.createUserWithEmailAndPassword(email, password)

    override fun resetPassword(email: String) =
        fireBaseAuth.sendPasswordResetEmail(email)
}