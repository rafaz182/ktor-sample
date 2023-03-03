package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val email       = varchar("email", 128)
    val password    = varchar("password", 128)
    val nick        = varchar("nick", 16)
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    val email       by Users.email
    val password    by Users.password
    val nick        by Users.nick
}