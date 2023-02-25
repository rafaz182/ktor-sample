package dev.rafaz.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.rafaz.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.loginRouting(audience: String, issuer: String, secret: String) {
    route(path = "/login") {
        post {
            val user = call.receive<User>()

            if (user.id != "rafa" || user.password != "1337") {
                return@post call.respondText(
                    text = "Dados incorretos",
                    status = HttpStatusCode.Unauthorized
                )
            }

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim(ID_CLAIM_KEY, user.id)
                .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                .sign(Algorithm.HMAC256(secret))

            call.respond(hashMapOf("token" to token))
        }
    }
    authenticate {
        get("/hello") {
            val principal = call.principal<JWTPrincipal>()
            val username = principal!!.payload.getClaim(ID_CLAIM_KEY).asString()
            val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
            call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
        }
    }
}

const val ID_CLAIM_KEY = "id"