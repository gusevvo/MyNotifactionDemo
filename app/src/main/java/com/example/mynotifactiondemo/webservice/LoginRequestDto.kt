package com.example.mynotifactiondemo.webservice

data class LoginRequestDto(val login: String, val password: String, val captcha: String = "")