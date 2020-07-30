package com.example.mynotifactiondemo.data.repository

import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyTransportationsRepository @Inject constructor(
    private val apiClient: ApiClientInterface
) {
    suspend fun getMyTransportations(requestDto: MyTransportationsRequestDto) = apiClient.getMyTransportations(requestDto)
}