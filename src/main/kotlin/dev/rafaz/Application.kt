package dev.rafaz

import dev.rafaz.database.DatabaseFactory
import dev.rafaz.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    DatabaseFactory.init(environment.config)

    install(CORS) {
        //allowHeader()
    }
    install(SimplePlugin)

    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val myRealm = environment.config.property("jwt.realm").getString()

    installJWTAuth(myRealm, audience, issuer, secret)
    //install(Session)

    configureRouting(audience, issuer, secret)
}
