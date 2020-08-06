package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationResponseInfoDto(
    val cargoType: String,
    val loadingType: String,
    val trailerVolume: String,
    val trailerType: String,
    val carryingCapacity: String,
    val details: String
)