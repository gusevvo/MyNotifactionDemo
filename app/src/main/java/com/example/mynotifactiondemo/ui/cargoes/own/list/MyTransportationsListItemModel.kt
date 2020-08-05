package com.example.mynotifactiondemo.ui.cargoes.own.list

data class MyTransportationsListItemModel(
    val id: String,
    val numberAndStatusChangeDate: String,
    val statusText: String,
    val statusTextColor: Int,
    val cost: String,
    val costWithoutVat: String,
    val cityLoading: String,
    val cityUnloading: String,
    val dateLoading: String,
    val dateUnloading: String,
    val routeNodesCount: Int
)