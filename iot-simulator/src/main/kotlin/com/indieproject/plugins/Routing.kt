package com.indieproject.plugins

import com.indieproject.routes.environmentRoutes
import com.indieproject.routes.healthCheck
import com.indieproject.routes.homeRoute
import com.indieproject.routes.monitorRoutes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {

    routing {
      monitorRoutes()
      environmentRoutes()
      homeRoute()
      healthCheck()
    }
}
