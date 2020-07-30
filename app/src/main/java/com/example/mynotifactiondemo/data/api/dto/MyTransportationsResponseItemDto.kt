package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationsResponseItemDto (
    val id: String,
    val number: String,
    val status: Int,
    val statusChangeTime: String,
    val tariffWithVat: Int,
    val tariff: Int,
    val cityLoading: String,
    val cityUnloading: String,
    val dateLoading: String,
    val dateUnloading: String,
    val routeNodesCount: Int
)