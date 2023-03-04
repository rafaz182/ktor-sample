package dev.rafaz.database.daoimpl

import dev.rafaz.database.DatabaseFactory.dbQuery
import dev.rafaz.database.GeneralAttributesDAO
import dev.rafaz.database.GeneralOptionsDAO
import dev.rafaz.database.TypeTransformation.toData
import dev.rafaz.database.tables.CategoriesGeneralAttributes
import dev.rafaz.database.tables.GeneralAttributeEntity
import dev.rafaz.database.tables.GeneralAttributes
import dev.rafaz.models.GeneralAttribute
import dev.rafaz.models.GeneralOption
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class GeneralAttributesDAOImpl(private val generalOptionsDAO: GeneralOptionsDAO) : GeneralAttributesDAO {
    private fun resultRowToGeneralAttribute(gaRow: ResultRow, options: List<GeneralOption>): GeneralAttribute {
        return GeneralAttribute(
            id = gaRow[CategoriesGeneralAttributes.attribute].value,
            name = gaRow[GeneralAttributes.name],
            description = gaRow[GeneralAttributes.description],
            options = options
        )
    }
    /**
     * Exposed DAO can't handle composite columns, that's why we are using Table instead
     *  https://github.com/JetBrains/Exposed/issues/964
     *  https://github.com/JetBrains/Exposed/wiki/DSL
     *
     * SELECT [CategoriesGeneralAttributes.attribute], [GeneralAttributes.name], [GeneralAttributes.description]
     * FROM [GeneralAttributes]
     * INNER JOIN [CategoriesGeneralAttributes]
     *  ON [CategoriesGeneralAttributes.attribute] = [GeneralAttributes.id]
     * WHERE [CategoriesGeneralAttributes.category] = [categoryId]
     *
     * TODO: use DAO when jetbrains deliverer it
     */
    override suspend fun allAttributesByCategory(categoryId: Int): List<GeneralAttribute> = dbQuery {
        GeneralAttributes.join(CategoriesGeneralAttributes, JoinType.INNER) {
            GeneralAttributes.id eq CategoriesGeneralAttributes.attribute
        }
            .slice(CategoriesGeneralAttributes.attribute, GeneralAttributes.name, GeneralAttributes.description)
            .select { CategoriesGeneralAttributes.category eq categoryId }
            .map { gaRow ->
                val attributeId = gaRow[CategoriesGeneralAttributes.attribute].value
                val options = generalOptionsDAO.allOptions(attributeId).map { it.toData() }
                resultRowToGeneralAttribute(gaRow, options)
            }
    }

    override suspend fun allAttributes(): List<GeneralAttributeEntity> = dbQuery {
        GeneralAttributeEntity.all().toList()
    }

    override suspend fun newAttribute(name: String, description: String): GeneralAttributeEntity = dbQuery {
        GeneralAttributeEntity.new {
            this.name = name
            this.description = description
        }
    }

    override suspend fun editAttribute(attributeId: Int, name: String?, description: String?): Boolean = dbQuery {
        if (name.isNullOrEmpty() && description.isNullOrEmpty())
            return@dbQuery false

        GeneralAttributeEntity.findById(attributeId)?.let {
            if (!name.isNullOrEmpty()) it.name = name
            if (!description.isNullOrEmpty()) it.description = description
            true
        } ?: false
    }

    override suspend fun deleteAttribute(attributeId: Int): Boolean = dbQuery {
        GeneralAttributeEntity.findById(attributeId)?.delete() ?: return@dbQuery false
        true
    }

    override suspend fun attachToACategory(categoryId: Int, attributeId: Int): Boolean = dbQuery {
        val insertStatement = CategoriesGeneralAttributes.insert {
            it[CategoriesGeneralAttributes.category] = categoryId
            it[CategoriesGeneralAttributes.attribute] = attributeId
        }
        insertStatement.resultedValues?.singleOrNull() ?: return@dbQuery false
        true
    }
}