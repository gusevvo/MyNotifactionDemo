package com.example.mynotifactiondemo.data.api.dto

data class LoginRequestDto(val login: String, val password: String, val captcha: String = "")