package com.facundo.taskflow_api.service

import com.facundo.taskflow_api.dto.AuthResponse
import com.facundo.taskflow_api.dto.LoginRequest
import com.facundo.taskflow_api.model.User
import com.facundo.taskflow_api.repository.UserRepository
import com.facundo.taskflow_api.security.JwtService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {
    //hash for each
    private val encoder = BCryptPasswordEncoder()

    fun register(user: User): AuthResponse {
        val existing = userRepository.findByEmail(user.email)
        if (existing != null) throw Exception("Email ya registrado")
        val hashed = user.copy(password = encoder.encode(user.password) ?: throw Exception("Error al encriptar password"))
        val saved = userRepository.save(hashed)
        val token = jwtService.generateToken(saved.email)
        return AuthResponse(token)
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw Exception("Usuario no encontrado")
        if (!encoder.matches(request.password, user.password))
            throw Exception("Password incorrecto")
        val token = jwtService.generateToken(user.email)
        return AuthResponse(token)
    }
}