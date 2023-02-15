package com.redpine.home.data

import com.google.firebase.auth.FirebaseAuth
import com.redpine.home.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor( private val auth:FirebaseAuth):AuthRepository {

    override fun authEmail(email:String, password:String)=
        auth.signInWithEmailAndPassword(email,password)

    override fun getUser() = auth.currentUser

    override fun createUser(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email,password)

    fun
}