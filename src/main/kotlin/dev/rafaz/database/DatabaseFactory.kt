package dev.rafaz.database

import dev.rafaz.models.Articles
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(url: String, driver: String) {
        val database = Database.connect(url, driver)
        transaction(database) {
            addLogger(StdOutSqlLogger, CompositeSqlLogger(), Slf4jSqlDebugLogger)
            SchemaUtils.create(Articles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}