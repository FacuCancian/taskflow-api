package com.facundo.taskflow_api.service

import com.facundo.taskflow_api.model.Category
import com.facundo.taskflow_api.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun getAllByUser(userId: Long): List<Category> =
        categoryRepository.findByUserId(userId)

    fun create(category: Category): Category =
        categoryRepository.save(category)

    fun delete(id: Long) = categoryRepository.deleteById(id)
}