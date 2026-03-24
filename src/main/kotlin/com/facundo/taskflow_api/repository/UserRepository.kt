package com.facundo.taskflow_api.repository

import com.facundo.taskflow_api.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{//mange user that has long id
    fun findByEmail (email: String): User?
}