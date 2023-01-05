package com.example.forage_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.auth.AuthRepo
import com.example.forage_compose.domain.auth.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepo
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow : StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow : StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser : FirebaseUser?
         get() = repo.currentUser

    init {
        if (repo.currentUser != null){
            _loginFlow.value = Resource.Success(repo.currentUser!!)
        }
    }

    fun login(email : String, password : String){
        viewModelScope.launch {
            _loginFlow.value = Resource.Loading
            val result = repo.login(email, password)
            _loginFlow.value = result
        }
    }

    fun signup(name : String, email : String, password : String){
        viewModelScope.launch {
            _signupFlow.value = Resource.Loading
            val result = repo.signUp(email, name, password)
            _signupFlow.value = result

        }
    }

    fun logout(){
        repo.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}
