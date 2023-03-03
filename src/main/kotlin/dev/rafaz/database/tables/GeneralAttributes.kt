package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object GeneralAttributes : IntIdTable() {
    val name        = varchar("name", 64)
    val description = varchar("description", 512)
}

class GeneralAttributeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GeneralAttributeEntity>(GeneralAttributes)

    val name        by GeneralAttributes.name
    val description by GeneralAttributes.description
    val options     by GeneralOptionEntity referrersOn GeneralOptions.attribute
}