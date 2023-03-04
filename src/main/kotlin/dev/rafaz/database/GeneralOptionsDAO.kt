package dev.rafaz.database

import dev.rafaz.database.tables.GeneralOptionEntity
import dev.rafaz.database.tables.GeneralOptions
import dev.rafaz.models.GeneralOption
import org.jetbrains.exposed.sql.ResultRow

interface GeneralOptionsDAO {
    suspend fun allOptions(attributeId: Int): List<GeneralOptionEntity>
    suspend fun addOption(attributeId: Int, option: String): GeneralOptionEntity
    suspend fun editOption(optionId: Int, option: String): Boolean
    suspend fun deleteOption(optionId: Int): Boolean
}