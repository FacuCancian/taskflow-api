package com.facundo.taskflow_api.service

import com.facundo.taskflow_api.dto.CategoryResponse
import com.facundo.taskflow_api.dto.TaskResponse
import com.facundo.taskflow_api.dto.UserResponse
import com.facundo.taskflow_api.model.Task
import com.facundo.taskflow_api.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    private fun Task.toResponse() = TaskResponse(
        id = id,
        title = title,
        description = description,
        completed = completed,
        createdAt = createdAt,
        user = user?.let { UserResponse(it.id, it.email, it.name) },
        category = category?.let { CategoryResponse(it.id, it.name) }
    )

    fun getAllByUser(userId: Long): List<TaskResponse> =
        taskRepository.findByUserId(userId).map { it.toResponse() }

    fun getByUserAndCompleted(userId: Long, completed: Boolean): List<TaskResponse> =
        taskRepository.findByUserIdAndCompleted(userId, completed).map { it.toResponse() }

    fun create(task: Task): TaskResponse =
        taskRepository.save(task).toResponse()

    fun update(id: Long, updated: Task): TaskResponse {
        val existing = taskRepository.findById(id)
            .orElseThrow { Exception("Tarea no encontrada") }
        return taskRepository.save(
            existing.copy(
                title = updated.title,
                description = updated.description,
                category = updated.category
            )
        ).toResponse()
    }

    fun complete(id: Long): TaskResponse {
        val task = taskRepository.findById(id)
            .orElseThrow { Exception("Tarea no encontrada") }
        return taskRepository.save(task.copy(completed = true)).toResponse()
    }

    fun delete(id: Long) = taskRepository.deleteById(id)
}