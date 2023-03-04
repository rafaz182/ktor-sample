package dev.rafaz.database

import dev.rafaz.database.tables.CategoryEntity
import dev.rafaz.models.Category

interface CategoryDAO {
    suspend fun getCategory(categoryId: Int): Category?
    suspend fun allCategories(): List<Category>
    suspend fun getCategories(): List<Category>
    suspend fun getCategories(parentId: Int): List<Category>
    suspend fun newCategory(parentId: Int?, name: String): CategoryEntity
    suspend fun addAttribute(categoryId: Int, attributeId: Int): Boolean
}
