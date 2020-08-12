package com.example.mynotifactiondemo.data.api

import com.example.mynotifactiondemo.data.api.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClientInterface {
    @POST("UI_FortisCore/api/Registration/Login")
    suspend fun login(@Body requestDto: LoginRequestDto): String

    @POST("UI_FortisCore/api/Registration/Logout")
    suspend fun logout(): String

    @GET("UI_FortisCore/api/User/GetUserInfo")
    suspend fun getUser(): UserResponseDto

    @POST("UI/Fortis/api/Cargoes/Own/Get")
    suspend fun getMyTransportations(@Body requestDto: MyTransportationsRequestDto): MyTransportationsResponseDto

    @GET("UI/Fortis/api/Cargoes/Own/GetCargoVersion")
    suspend fun getMyTransportation(@Query("id") id: String): MyTransportationResponseDto

    @POST("UI/Fortis/api/Cargoes/Own/SendCode")
    suspend fun requestVerificationCode(@Body requestDto: RequestVerificationCodeRequestDto)

    @POST("UI/Fortis/api/Cargoes/Own/Reject")
    suspend fun rejectMyTransportation(@Body requestDto: MyTransportationRejectRequestDto)

    @POST("UI/Fortis/api/Cargoes/Own/VerifyCode")
    suspend fun acceptMyTransportation(@Body requestDto: MyTransportationAcceptRequestDto): MyTransportationResponseDto
}

