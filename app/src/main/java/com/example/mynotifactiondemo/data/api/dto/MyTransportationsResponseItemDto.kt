package com.example.mynotifactiondemo.data.api.dto

import java.util.*

data class MyTransportationsResponseItemDto (
    val id: String,
    val number: String,
    val status: Int,
    val statusChangeTime: Date,
    val tariffWithVat: Double,
    val tariff: Double,
    val cityLoading: String,
    val cityUnloading: String,
    val dateLoading: Date,
    val dateUnloading: Date,
    val routeNodesCount: Int
)