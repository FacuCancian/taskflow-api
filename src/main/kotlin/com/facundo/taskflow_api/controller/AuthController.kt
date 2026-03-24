package com.facundo.taskflow_api.controller

import com.facundo.taskflow_api.dto.AuthResponse
import com.facundo.taskflow_api.dto.LoginRequest
import com.facundo.taskflow_api.model.User
import com.facundo.taskflow_api.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(@RequestBody user: User): AuthResponse =
        authService.register(user)

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): AuthResponse =
        authService.login(request)
}