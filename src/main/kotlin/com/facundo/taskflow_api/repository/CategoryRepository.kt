package com.facundo.taskflow_api.repository

import com.facundo.taskflow_api.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByUserId(userId: Long): List<Category>
}