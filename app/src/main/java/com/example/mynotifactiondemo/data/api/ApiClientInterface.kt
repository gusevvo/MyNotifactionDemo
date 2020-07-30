package com.example.mynotifactiondemo.data.api

import com.example.mynotifactiondemo.data.api.dto.LoginRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClientInterface {
    @POST("UI_FortisCore/api/Registration/Login")
    suspend fun login(@Body requestDto: LoginRequestDto): String

    @POST("UI_FortisCore/api/Registration/Logout")
    suspend fun logout(): String

    @POST("UI/Fortis/api/Cargoes/Own/Get")
    suspend fun getMyTransportations(@Body requestDto: MyTransportationsRequestDto): MyTransportationsResponseDto
}

