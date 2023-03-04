package dev.rafaz.database.daoimpl

import dev.rafaz.database.CategoryDAO
import dev.rafaz.database.DatabaseFactory.dbQuery
import dev.rafaz.database.TypeTransformation.toData
import dev.rafaz.database.tables.Categories
import dev.rafaz.database.tables.CategoriesGeneralAttributes
import dev.rafaz.database.tables.CategoryEntity
import dev.rafaz.models.Category
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert

class CategoryDAOImpl : CategoryDAO {
    override suspend fun getCategory(categoryId: Int): Category? = dbQuery {
        CategoryEntity.findById(categoryId)?.toData(true)
    }

    override suspend fun allCategories(): List<Category> = dbQuery {
        CategoryEntity.all().map { it.toData(true) }
    }

    override suspend fun getCategories(): List<Category> = dbQuery {
        CategoryEntity.find { Categories.parentId.isNull() }.map { it.toData() }
    }

    override suspend fun getCategories(parentId: Int): List<Category> = dbQuery {
        CategoryEntity.find {
            Categories.parentId eq parentId
        }.map { it.toData(true) }
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
}