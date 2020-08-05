package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationResponseDto(
    val id: String,
    val number: String,
    val forceDate: String,
    val contract: MyTransportationResponseContractDto,
    val status: Int,
    val customer: MyTransportationResponseCustomerDto,
    val carrier: MyTransportationResponseCarrierDto
)
