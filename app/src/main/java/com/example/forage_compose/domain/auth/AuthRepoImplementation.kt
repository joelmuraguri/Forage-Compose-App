package com.example.forage_compose.domain.auth

import com.example.forage_compose.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepoImplementation @Inject constructor(
    private val auth : FirebaseAuth
) : AuthRepo {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signUp(
        email: String,
        name: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result =  auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            return Resource.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }

    }

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result =  auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }

    }


    override fun logout() {
        auth.signOut()
    }
}