package dev.rafaz.routes

import dev.rafaz.database.DAO
import dev.rafaz.dtos.ArticleDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.articleRouting() {
    route(path = "/article") {
        get {
            val articles = DAO.Article.allArticles()
            call.respond(articles)
        }
        post {
            val article = call.receive<ArticleDTO>()

            val insertResult = DAO.Article.addNewArticle(article.title, article.body)

            if (insertResult != null) {
                call.respondText(
                    text = "Article stored correctly",
                    status = HttpStatusCode.Created
                )
            } else {
                call.respondText(
                    text = "Error while storing article",
                    status = HttpStatusCode.TooManyRequests
                )
            }
        }
    }
}