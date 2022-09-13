package com.indieproject.routes

import com.indieproject.utils.MonitorUtils
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*

/**
 * This program generates a post route to send
 * a MonitorData object back to the client application.
 *
 * @throws BaseApplicationResponse.ResponseAlreadySentException
 * An exception thrown when the same request is called multiple times.
 */
fun Application.monitorRoutes()  {
  routing {
    get("/monitor") {
      try {
        val utils = MonitorUtils()
        call.respond(utils.generateMonitorObject())
      } catch(B : BaseApplicationResponse.ResponseAlreadySentException) {}
    }
  }
}
