package com.example.forage_compose.domain.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepo {

    val currentUser : FirebaseUser?
    suspend fun signUp(email : String, name : String, password : String) : Resource<FirebaseUser>
    suspend fun login(email : String, password : String) : Resource<FirebaseUser>
    fun logout()




}