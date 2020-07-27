package com.example.mynotifactiondemo.data.repository

import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.LoginRequestDto
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiClient: ApiClientInterface
) {

    suspend fun login(username: String, password: String) {
        val requestDto = LoginRequestDto(username, password, "")
        apiClient.login(requestDto)
    }

    suspend fun logout() {
        apiClient.logout()
    }
}