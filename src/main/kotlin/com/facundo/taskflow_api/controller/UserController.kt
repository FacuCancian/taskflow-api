package com.facundo.taskflow_api.controller

import com.facundo.taskflow_api.model.User
import com.facundo.taskflow_api.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): User =
        userRepository.save(user)

    @GetMapping
    fun getAll(): List<User> = userRepository.findAll()
}