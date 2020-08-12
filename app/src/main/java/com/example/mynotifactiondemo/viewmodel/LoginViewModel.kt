package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mynotifactiondemo.data.api.dto.UserResponseDto
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

    val user: LiveData<ViewModelResult<UserResponseDto>> = liveData {
        emit(ViewModelResult.loading())
        try {
            emit(ViewModelResult.success(usersRepository.getUser()))
        } catch(throwable: Throwable) {
            emit(ViewModelResult.failure(throwable))
        }
    }

    init {
        try {
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