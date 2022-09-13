package com.indieproject.routes

import com.indieproject.utils.EnvironmentUtils
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*

/**
 * This program generates a post route to send
 * an EnvironmentData object back to the client application.
 *
 * @throws BaseApplicationResponse.ResponseAlreadySentException
 * An exception thrown when the same request is called multiple times.
 */
fun Application.environmentRoutes() {
  routing {
    get("/environment") {
      try {
        val utils = EnvironmentUtils()
        call.respond(utils.generateEnvironmentObject())
      } catch (B: BaseApplicationResponse.ResponseAlreadySentException) {}
    }
  }
}