package com.indieproject.cordapp.webserver.utils

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.indieproject.cordapp.webserver.routes.msgRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.corda.client.jackson.JacksonSupport
import net.corda.client.rpc.CordaRPCConnection
import net.corda.core.messaging.CordaRPCOps
import org.slf4j.event.Level
import java.util.concurrent.TimeUnit

fun Application.mainModule() {
  val connection: CordaRPCConnection = connectToNode()
  install(CallLogging) { level = Level.INFO }
  routing { msgRouting(connection.proxy) }
  addShutdownEvent(connection)
}

fun Application.addShutdownEvent(connection: CordaRPCConnection) {
  environment.monitor.subscribe(ApplicationStopped) {
    connection.notifyServerAndClose()
  }
}

fun ContentNegotiation.Configuration.cordaJackson(proxy: CordaRPCOps) {
  val mapper: ObjectMapper = JacksonSupport.createDefaultMapper(proxy)
  mapper.apply {
    setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
      indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
      indentObjectsWith(DefaultIndenter("  ", "\n"))
    })
  }
  val converter = JacksonConverter(mapper)
  register(ContentType.Application.Json, converter)
}

fun NettyApplicationEngine.addShutdownHook() {
  Runtime.getRuntime().addShutdownHook(Thread {
    stop(1, 1, TimeUnit.SECONDS)
  })
  Thread.currentThread().join()
}