package dev.rafaz.database.daoimpl

import dev.rafaz.database.CategoryDAO
import dev.rafaz.database.DatabaseFactory.dbQuery
import dev.rafaz.database.tables.CategoriesGeneralAttributes
import dev.rafaz.database.tables.CategoryEntity
import org.jetbrains.exposed.sql.insert

class CategoryDAOImpl : CategoryDAO {
    override suspend fun allCategories(): List<CategoryEntity> = dbQuery {
        CategoryEntity.all().toList()
    }

    override suspend fun newCategory(parentId: Int?, name: String): CategoryEntity = dbQuery {
        CategoryEntity.new {
            this.parent = if (parentId != null) CategoryEntity[parentId] else null
            this.name = name
        }
    }

    override suspend fun addAttribute(categoryId: Int, attributeId: Int): Boolean = dbQuery {
        val insertStatement = CategoriesGeneralAttributes.insert {
            it[CategoriesGeneralAttributes.category] = categoryId
            it[CategoriesGeneralAttributes.attribute] = attributeId
        }
        insertStatement.resultedValues?.singleOrNull() ?: return@dbQuery false
        true
    }

    override suspend fun getCategory(categoryId: Int): CategoryEntity? {
        return CategoryEntity.findById(categoryId)
    }
}