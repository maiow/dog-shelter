package com.redpine.home.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

/*
    @Provides
    fun providesOnBoardingRepository() = FirebaseAuth.getInstance()
*/


    @Provides
    fun providesFireBase() = Firebase.auth
}