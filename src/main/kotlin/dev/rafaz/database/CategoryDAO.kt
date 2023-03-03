package dev.rafaz.database

import dev.rafaz.database.tables.CategoryEntity

interface CategoryDAO {
    suspend fun allCategories(): List<CategoryEntity>
    suspend fun newCategory(parentId: Int?, name: String, attributeId: Int?): CategoryEntity
    suspend fun addAttribute(categoryId: Int, attributeId: Int): Boolean
}
