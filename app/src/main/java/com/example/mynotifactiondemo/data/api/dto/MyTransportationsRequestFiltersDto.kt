package com.example.mynotifactiondemo.data.api.dto

data class MyTransportationsRequestFiltersDto(
    val isActualOnly: Boolean,
    val statuses: Collection<Int>,
    val search: String
)