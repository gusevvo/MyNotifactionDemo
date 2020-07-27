package com.example.mynotifactiondemo.data.api

import com.example.mynotifactiondemo.data.api.dto.LoginRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClientInterface {
    @POST("UI_FortisCore/api/Registration/Login")
    suspend fun login(@Body requestDto: LoginRequestDto): String

    @POST("UI_FortisCore/api/Registration/Logout")
    suspend fun logout(): String
}

