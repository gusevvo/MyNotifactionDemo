package com.example.mynotifactiondemo.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClientInterface {
    @POST("UI_FortisCore/api/Registration/Login")
    suspend fun login(@Body requestDto: LoginRequestDto): String
}

