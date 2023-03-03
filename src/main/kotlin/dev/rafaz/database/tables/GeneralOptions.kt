package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object GeneralOptions : IntIdTable() {
    val attribute   = reference("id_attribute", GeneralAttributes, onDelete = ReferenceOption.CASCADE)
    val value       = varchar("value", 64)
}

class GeneralOptionEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GeneralOptionEntity>(GeneralOptions)

    var value       by GeneralOptions.value
    var attribute   by GeneralAttributeEntity referencedOn GeneralOptions.attribute
}