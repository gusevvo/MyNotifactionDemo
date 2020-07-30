package com.example.mynotifactiondemo.ui.cargoes.own

data class TransportationListItemModel(
    val id: String,
    val status: Int,
    val numberAndStatusChangeDate: String,
    val cost: String,
    val costWithoutVat: String,
    val statusText: String,
    val cityLoading: String,
    val cityUnloading: String,
    val dateLoading: String,
    val dateUnloading: String,
    val routeNodesCount: Int
)