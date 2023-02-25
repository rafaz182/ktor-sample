package dev.rafaz.plugins

import dev.rafaz.database.ArticleDAO
import dev.rafaz.routes.articleRouting
import dev.rafaz.routes.customerRouting
import dev.rafaz.routes.loginRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting(audience: String, issuer: String, secret: String, articleDAO: ArticleDAO) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting()
        loginRouting(audience, issuer, secret)
        articleRouting(articleDAO)
    }
}
