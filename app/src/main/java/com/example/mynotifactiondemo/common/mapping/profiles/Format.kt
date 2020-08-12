package com.example.mynotifactiondemo.common.mapping.profiles

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Format {
    private val currency = NumberFormat.getCurrencyInstance(Locale("ru")).apply {
        currency = Currency.getInstance("RUB")
    }
    private val date = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("ru"))

    fun Currency(value: Double): String = currency.format(value)

    fun Date(value: Date): String = date.format(value)
}