package com.example.mynotifactiondemo.ui.cargoes.own

import java.util.*

data class TransportationListItemModel(
    val id: UUID,
    val numberAndStatusChangeDate: String,
    val cost: String,
    val costWithoutVat: String,
    val status: Int,
    val statusText: String,
    val cityLoading: String,
    val cityUnloading: String,
    val dateLoading: String,
    val dateUnloading: String,
    val routeNodesCount: Int
)