package com.example.mynotifactiondemo.data.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @see <a href="https://developer.android.com/reference/java/text/SimpleDateFormat">SimpleDateFormat</a>
 */
class DateJsonAdapter {
    @ToJson
    fun toJson(value: Date): String = FORMATTER_WITH_TIMEZONE.format(value)

    @FromJson
    fun fromJson(value: String): Date {
        return try {
            FORMATTER.parse(value)
        } catch (ex: ParseException) {
            try {
                FORMATTER_WITH_TIMEZONE.parse(value)
            } catch (ex: ParseException) {
                try {
                    SHORT_FORMATTER_WITH_TIMEZONE.parse(value)
                } catch (ex: ParseException) {
                    FORMATTER_WITHOUT_TIMEZONE.parse(value)
                }
            }
        }
    }

    companion object {
        private val FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private val FORMATTER_WITH_TIMEZONE = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        private val SHORT_FORMATTER_WITH_TIMEZONE = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        private val FORMATTER_WITHOUT_TIMEZONE = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

    }
}