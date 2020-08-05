package com.example.mynotifactiondemo.common.mappingprofiles

import java.lang.IllegalArgumentException

fun Int.toStatusText(): String = when (this) {
    0 -> "Новый"
    1 -> "На подтверждении"
    2 -> "На исполнении"
    3 -> "Требуется переподписание"
    4 -> "Отклонена"
    5 -> "Ожидание документов"
    6 -> "Проблема с документами"
    7 -> "Оплата до"
    8 -> "Оплачена"
    9 -> "Устраните замечания"
    else -> throw IllegalArgumentException("Status `$this` has not matched any text")
}

fun Int.toStatusColor(): Int = when (this) {
    3, 4 -> COLOR_RED
    1 -> COLOR_GRAY
    2 -> COLOR_BLUE
    else -> COLOR_GRAY
}

const val COLOR_RED = 0xffde350b.toInt()
const val COLOR_BLUE = 0xff4c9aff.toInt()
const val COLOR_GRAY = 0xff708096.toInt()
