package com.example.mynotifactiondemo.data.api.dto

data class RequestVerificationCodeRequestDto(
    val documentId: String,
    val deviceToken: String
)