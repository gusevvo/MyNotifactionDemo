package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotifactiondemo.data.repository.UsersRepository
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    enum class AuthenticationStates {
        AUTHENTICATED,
        UNAUTHENTICATED,
        INVALID_AUTHENTICATION
    }

    val authenticationState = MutableLiveData<AuthenticationStates>()

    init {
        try {
            authenticationState.value =
                if (usersRepository.isAuthenticated) AuthenticationStates.AUTHENTICATED else AuthenticationStates.UNAUTHENTICATED
        } catch (t: Throwable) {
            authenticationState.value = AuthenticationStates.INVALID_AUTHENTICATION
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                usersRepository.login(username, password)
                authenticationState.postValue(AuthenticationStates.AUTHENTICATED)
            } catch (ex: Throwable) {
                authenticationState.postValue(AuthenticationStates.INVALID_AUTHENTICATION)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                usersRepository.logout()
                authenticationState.postValue(AuthenticationStates.UNAUTHENTICATED)
            } catch (ex: Throwable) {
                authenticationState.postValue(AuthenticationStates.INVALID_AUTHENTICATION)
            }
        }
    }
}