package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationsRequestDto(
    val from: Int,
    val to: Int,
    val filters: MyTransportationsRequestFiltersDto
)