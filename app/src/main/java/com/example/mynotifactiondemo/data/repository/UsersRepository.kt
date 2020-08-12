package com.example.mynotifactiondemo.data.repository

import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.dto.LoginRequestDto
import com.example.mynotifactiondemo.data.preferences.UserCredentialsPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val apiClient: ApiClientInterface,
    private val userCredentialsPreferences: UserCredentialsPreferences
) {

    val isAuthenticated: Boolean
        get() = userCredentialsPreferences.isAuthenticated

    suspend fun login(username: String, password: String) {
        val requestDto =
            LoginRequestDto(
                username,
                password,
                ""
            )
        apiClient.login(requestDto)
        userCredentialsPreferences.login(username, password)
    }

    suspend fun logout() {
        apiClient.logout()
        userCredentialsPreferences.logout()
    }

    suspend fun getUser() = apiClient.getUser()
}