package com.facundo.taskflow_api.repository

import com.facundo.taskflow_api.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Long>{
    fun findByUserId(userId: Long): List<Task>
    fun findByUserIdAndCompleted(userId: Long, completed: Boolean): List<Task>

}