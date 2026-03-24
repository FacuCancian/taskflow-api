package com.facundo.taskflow_api.dto

data class LoginRequest(
    val email: String = "",
    val password: String = ""
)