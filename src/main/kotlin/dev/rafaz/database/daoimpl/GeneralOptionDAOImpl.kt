package dev.rafaz.database.daoimpl

import dev.rafaz.database.DatabaseFactory.dbQuery
import dev.rafaz.database.GeneralOptionsDAO
import dev.rafaz.database.tables.GeneralAttributeEntity
import dev.rafaz.database.tables.GeneralOptionEntity
import dev.rafaz.database.tables.GeneralOptions

class GeneralOptionDAOImpl : GeneralOptionsDAO {
    override suspend fun allOptions(attributeId: Int): List<GeneralOptionEntity> = dbQuery {
        GeneralOptionEntity.find { GeneralOptions.attribute eq attributeId }.toList()
    }

    override suspend fun addOption(attributeId: Int, option: String): GeneralOptionEntity = dbQuery {
        GeneralOptionEntity.new {
            value = option
            attribute = GeneralAttributeEntity[attributeId]
        }
    }

    override suspend fun editOption(optionId: Int, option: String): Boolean = dbQuery {
        GeneralOptionEntity.findById(optionId)?.let{
            it.value = option
            true
        } ?: false
    }

    override suspend fun deleteOption(optionId: Int): Boolean = dbQuery {
        GeneralOptionEntity.findById(optionId)?.delete() ?: return@dbQuery false
        true
    }
}