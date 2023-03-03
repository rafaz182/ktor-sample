package dev.rafaz.database.tables

import org.jetbrains.exposed.sql.Table

object Orders : Table() {
    val id = integer("id").autoIncrement()

}