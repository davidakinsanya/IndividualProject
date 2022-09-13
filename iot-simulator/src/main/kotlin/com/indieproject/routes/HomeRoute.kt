package com.indieproject.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*

/**
 * This program instantiates a base route for
 * the server.
 *
 * @throws BaseApplicationResponse.ResponseAlreadySentException
 * An exception thrown when the same request is called multiple times.
 */
fun Application.homeRoute()  {
  routing {
    get("/") {
      try {
        call.respondText("Hello Ktor!")
      } catch(B : BaseApplicationResponse.ResponseAlreadySentException) {}
    }
  }
}
