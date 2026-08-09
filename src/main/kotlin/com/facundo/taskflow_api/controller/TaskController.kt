package com.facundo.taskflow_api.controller

import com.facundo.taskflow_api.dto.TaskResponse
import com.facundo.taskflow_api.model.Task
import com.facundo.taskflow_api.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping
    fun getAll(@RequestParam userId: Long): List<TaskResponse> =
        taskService.getAllByUser(userId)

    @GetMapping("/filter")
    fun getByCompleted(
        @RequestParam userId: Long,
        @RequestParam completed: Boolean
    ): List<TaskResponse> = taskService.getByUserAndCompleted(userId, completed)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody task: Task): TaskResponse =
        taskService.create(task)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task): TaskResponse =
        taskService.update(id, task)

    @PatchMapping("/{id}/complete")
    fun complete(@PathVariable id: Long): TaskResponse =
        taskService.complete(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) =
        taskService.delete(id)
}