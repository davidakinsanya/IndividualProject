package com.indieproject.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*

/**
 * This program generates a get route to send
 * a health check to validate the server's health.
 *
 * @throws BaseApplicationResponse.ResponseAlreadySentException
 * An exception thrown when the same request is called multiple times.
 */
fun Application.healthCheck()  {
  routing {
    get("/health-check") {
      try {
        println("Check successful!")
        call.respond("Check successful!")
      } catch(B : BaseApplicationResponse.ResponseAlreadySentException) {}
    }
  }
}
