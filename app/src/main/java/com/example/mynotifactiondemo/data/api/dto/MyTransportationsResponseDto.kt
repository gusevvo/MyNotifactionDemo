package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationsResponseDto(
    val items: Collection<MyTransportationsResponseItemDto>,
    val totalCount: Int
)