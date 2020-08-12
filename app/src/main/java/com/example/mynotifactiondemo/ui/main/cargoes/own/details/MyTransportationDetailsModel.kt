package com.example.mynotifactiondemo.ui.main.cargoes.own.details

data class MyTransportationDetailsModel(
    val orderNumber: String,
    val orderDate: String,
    val contractNumberAndDate: String,
    val orderStatusText: String,
    val orderStatusTextColor: Int,
    val customerBankDetails: String,
    val carrierBankDetails: String,
    val cargoType: String,
    val loadingType: String,
    val trailerVolume: String,
    val trailerType: String,
    val carryingCapacity: String,
    val costWithoutVat: String,
    val paymentDueDate: String,
    val additionalRequirements: String
)