package com.example.mynotifactiondemo.data.api.dto

import java.util.*

data class MyTransportationResponseDto(
    val id: String,
    val number: String,
    val forceDate: Date,
    val contract: MyTransportationResponseContractDto,
    val status: Int,
    val customer: MyTransportationResponseCustomerDto,
    val carrier: MyTransportationResponseCarrierDto,
    val info: MyTransportationResponseInfoDto,
    val defaultPayment: MyTransportationResponseDefaultPaymentDto
)
