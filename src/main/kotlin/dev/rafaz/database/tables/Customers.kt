package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Customers : IntIdTable() {
    val firstName   = varchar("first_name", 30)
    val lastName    = varchar("last_name", 30)
}

class CustomerEntity(id: EntityID<Int>) : IntEntity(id) {
    val firstName   by Customers.firstName
    val lastName    by Customers.lastName
}