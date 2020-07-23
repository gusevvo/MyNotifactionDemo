package com.example.mynotifactiondemo.webservice

import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface IPortalApiService {
    @POST("UI_FortisCore/api/Registration/Login")
    suspend fun login(@Body requestDto: LoginRequestDto): String
}

