package com.facundo.taskflow_api.dto

import java.time.LocalDateTime

data class TaskResponse(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val createdAt: LocalDateTime,
    val user: UserResponse?,
    val category: CategoryResponse?
)