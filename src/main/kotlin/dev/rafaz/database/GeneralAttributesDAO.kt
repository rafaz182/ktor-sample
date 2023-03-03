package dev.rafaz.database

import dev.rafaz.database.tables.GeneralAttributeEntity
import dev.rafaz.models.GeneralAttribute

interface GeneralAttributesDAO {
    suspend fun allAttributesByCategory(categoryId: Int): List<GeneralAttribute>
    suspend fun allAttributes(): List<GeneralAttributeEntity>
    suspend fun newAttribute(name: String, description: String): GeneralAttributeEntity
    suspend fun editAttribute(attributeId: Int, name: String?, description: String?): Boolean
    suspend fun deleteAttribute(attributeId: Int): Boolean
}