package dev.rafaz.database.daoimpl

import dev.rafaz.database.ArticleDAO
import dev.rafaz.database.DatabaseFactory.dbQuery
import dev.rafaz.database.tables.Articles
import dev.rafaz.models.Article
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ArticleDAOImpl : ArticleDAO {

    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
        dateCreated = row[Articles.dateCreated].toLocalDateTime()
    )

    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun article(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id}) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }
}