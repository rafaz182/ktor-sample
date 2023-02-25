package dev.rafaz.models

import dev.rafaz.plugins.now
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

//https://stackoverflow.com/questions/67305843/kotlinx-serialization-deserializing-dates
//https://stackoverflow.com/questions/59952696/how-do-i-set-the-current-date-in-db-using-exposed-and-kotlin

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val dateCreated: LocalDateTime
)

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)
    val dateCreated = datetime("date_created").clientDefault { LocalDateTime.now() }

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}