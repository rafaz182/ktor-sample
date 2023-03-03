package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable() {
    val name        = varchar("name", 128)
    val description = varchar("description", 2048)
    val category    = reference("id_category", Categories)
}

class ProductEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductEntity>(Products)

    val name        by Products.name
    val description by Products.description
    val category    by CategoryEntity referencedOn Products.category
}