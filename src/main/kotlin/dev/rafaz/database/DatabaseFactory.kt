package dev.rafaz.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.rafaz.database.tables.*
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private fun createHikariDataSource(
        url: String,
        driver: String,
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })

    fun init(config: ApplicationConfig) {
        val url = config.property("database.url").getString()
        val driver = config.property("database.driver").getString()
        val database = Database.connect(createHikariDataSource(url = url, driver = driver))

        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Articles, Categories, CategoriesGeneralAttributes, Customers, GeneralAttributes, GeneralOptions, Products, Users)
        }
        runBlocking {
            with(DAO.Article) {
                if(allArticles().isEmpty()) {
                    addNewArticle("The drive to develop!", "...it's what keeps me going.")
                }
            }

            with(DAO.GeneralAttributes) {
                if (allAttributes().isNotEmpty()) return@with

                val attr1 = newAttribute(
                    name = "Volts", description = "Tensão do produto"
                )
                val option1 = DAO.GeneralOptions.addOption(attr1.id.value, "110v")
                val option2 = DAO.GeneralOptions.addOption(attr1.id.value, "220v")
                val option3 = DAO.GeneralOptions.addOption(attr1.id.value, "110v/220v")
                val option4 = DAO.GeneralOptions.addOption(attr1.id.value, "380v")

                val attr2 = newAttribute(
                    name = "Cor", description = "Cor do produto"
                )
                val option5 = DAO.GeneralOptions.addOption(attr2.id.value, "Branco")
                val option6 = DAO.GeneralOptions.addOption(attr2.id.value, "Preto")
                val option7 = DAO.GeneralOptions.addOption(attr2.id.value, "Azul")
                val option8 = DAO.GeneralOptions.addOption(attr2.id.value, "Amarelo")
                val option9 = DAO.GeneralOptions.addOption(attr2.id.value, "Vermelho")
            }

            with(DAO.Category) {
                if (allCategories().isNotEmpty()) return@with

                val cate1 = newCategory(null,  "Casa")

                val cate1_1 = newCategory(cate1.id.value, "Quarto")
                val cate1_2 = newCategory(cate1.id.value, "Banheiro")
                val cate1_3 = newCategory(cate1.id.value, "Sala")

                val cate1_1_1 = newCategory(cate1_1.id.value, "Colchão")
                addAttribute(cate1_1_1.id.value, 1)
                val cate1_1_2 = newCategory(cate1_1.id.value, "Travesseiro")
                addAttribute(cate1_1_2.id.value, 2)
                val cate1_1_3 = newCategory(cate1_1.id.value, "Lençóis")
                addAttribute(cate1_1_3.id.value, 2)
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}