package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotifactiondemo.data.repository.UsersRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    enum class AuthenticationStates {
        AUTHENTICATED,
        UNAUTHENTICATED,
        INVALID_AUTHENTICATION
    }

    val authenticationState = MutableLiveData<AuthenticationStates>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                usersRepository.login(username, password)
                authenticationState.postValue(AuthenticationStates.AUTHENTICATED)
            } catch (ex: Exception) {
                authenticationState.postValue(AuthenticationStates.INVALID_AUTHENTICATION)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                usersRepository.logout()
                authenticationState.postValue(AuthenticationStates.UNAUTHENTICATED)
            } catch (ex: Exception) {
                authenticationState.postValue(AuthenticationStates.INVALID_AUTHENTICATION)
            }
        }
    }
}