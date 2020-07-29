package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotifactiondemo.data.repository.UsersRepository
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }

    private val _authenticationState = MutableLiveData<ViewModelResult<AuthenticationState>>()
    val authenticationState: LiveData<ViewModelResult<AuthenticationState>>
        get() = _authenticationState

    init {
        try {Result
            _authenticationState.value =
                if (usersRepository.isAuthenticated)
                    ViewModelResult.success(AuthenticationState.AUTHENTICATED)
                else
                    ViewModelResult.success(AuthenticationState.UNAUTHENTICATED)
        } catch (throwable: Throwable) {
            _authenticationState.value = ViewModelResult.failure(throwable)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _authenticationState.postValue(ViewModelResult.loading())
            try {
                usersRepository.login(username, password)
                _authenticationState.postValue(ViewModelResult.success(AuthenticationState.AUTHENTICATED))
            } catch (throwable: Throwable) {
                _authenticationState.postValue(ViewModelResult.failure(throwable))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _authenticationState.postValue(ViewModelResult.loading())
            try {
                usersRepository.logout()
                _authenticationState.postValue(ViewModelResult.success(AuthenticationState.UNAUTHENTICATED))
            } catch (throwable: Throwable) {
                _authenticationState.postValue(ViewModelResult.failure(throwable))
            }
        }
    }
}