package com.example.mynotifactiondemo.data.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter {
    @ToJson
    fun toJson(value: Date): String = FORMATTER.format(value)

    @FromJson
    fun fromJson(value: String): Date {
        return try {
            FORMATTER.parse(value)
        } catch (ex: ParseException) {
            SHORT_FORMATTER.parse(value)
        }
    }

    companion object {
        private val FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private val SHORT_FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }
}