package com.facundo.taskflow_api.service

import com.facundo.taskflow_api.model.Task
import com.facundo.taskflow_api.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun getAllByUser(userId: Long): List<Task> =
        taskRepository.findByUserId(userId)

    fun getByUserAndCompleted(userId: Long, completed: Boolean): List<Task> =
        taskRepository.findByUserIdAndCompleted(userId, completed)

    fun create(task: Task): Task =
        taskRepository.save(task)

    fun update(id: Long, updated: Task): Task {
        val existing = taskRepository.findById(id)
            .orElseThrow { Exception("Tarea no encontrada") }
        return taskRepository.save(
            existing.copy(
                title = updated.title,
                description = updated.description,
                category = updated.category
            )
        )
    }

    fun complete(id: Long): Task {
        val task = taskRepository.findById(id)
            .orElseThrow { Exception("Tarea no encontrada") }
        return taskRepository.save(task.copy(completed = true))
    }

    fun delete(id: Long) = taskRepository.deleteById(id)
}