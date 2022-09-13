package com.indieproject.cordapp.webserver

import com.indieproject.cordapp.webserver.utils.addShutdownHook
import com.indieproject.cordapp.webserver.utils.mainModule
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    try {
        embeddedServer(Netty, port = 8080) {
            mainModule()
        }.start().addShutdownHook()
    } catch (e: Exception) {
        println(e.printStackTrace())
    }
}

