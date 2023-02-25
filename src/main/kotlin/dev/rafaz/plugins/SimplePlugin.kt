package dev.rafaz.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.*

val SimplePlugin = createApplicationPlugin(name = "SimplePlugin") {
    println("SimplePlugin is installed!")
    onCall { call ->
        call.request.origin.apply {
            println("Request URL: $scheme://$localHost:$localPort$uri")
        }
        call.response.headers.append("X-Custom-Header", "Hello, world!")
    }

    onCallReceive { call ->
        transformBody {

        }
    }
}