package com.facundo.taskflow_api.service

import com.facundo.taskflow_api.dto.AuthResponse
import com.facundo.taskflow_api.dto.LoginRequest
import com.facundo.taskflow_api.model.User
import com.facundo.taskflow_api.repository.UserRepository
import com.facundo.taskflow_api.security.JwtService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {
    fun register(user: User): AuthResponse {
        val existing = userRepository.findByEmail(user.email)
        if (existing != null) throw Exception("Email ya registrado")
        val saved = userRepository.save(user)
        val token = jwtService.generateToken(saved.email)
        return AuthResponse(token)
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw Exception("Usuario no encontrado")
        if (user.password != request.password)
            throw Exception("Password incorrecto")
        val token = jwtService.generateToken(user.email)
        return AuthResponse(token)
    }
}