package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationResponseDefaultPaymentDto(
    val cost: Double,
    val daysOffset: Int,
    val defermentStartType: Int,
    val defermentType: Int
)