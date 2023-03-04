package dev.rafaz.plugins

import dev.rafaz.routes.articleRouting
import dev.rafaz.routes.categoryRouting
import dev.rafaz.routes.customerRouting
import dev.rafaz.routes.loginRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(audience: String, issuer: String, secret: String) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting()
        loginRouting(audience, issuer, secret)
        articleRouting()
        categoryRouting()
    }
}
