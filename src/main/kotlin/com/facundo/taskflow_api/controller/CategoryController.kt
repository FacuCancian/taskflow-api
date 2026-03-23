package com.facundo.taskflow_api.controller

import com.facundo.taskflow_api.model.Category
import com.facundo.taskflow_api.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getAll(@RequestParam userId: Long): List<Category> =
        categoryService.getAllByUser(userId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody category: Category): Category =
        categoryService.create(category)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) =
        categoryService.delete(id)
}