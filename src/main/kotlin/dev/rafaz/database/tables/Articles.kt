package dev.rafaz.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)
    val dateCreated = datetime("date_created").clientDefault { DateTime.now() }

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}