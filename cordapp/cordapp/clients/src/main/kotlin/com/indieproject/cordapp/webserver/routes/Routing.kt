package com.indieproject.cordapp.webserver.routes

import com.indieproject.cordapp.flows.MsgFlowInitiator
import com.indieproject.cordapp.states.MsgState
import com.indieproject.cordapp.webserver.party.NetworkParty
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import net.corda.core.identity.Party
import net.corda.core.messaging.CordaRPCOps

fun Application.msgRouting(proxy: CordaRPCOps) {
  routing {
    post("/msg/{newMsg}") {
      try {
        val msg: String? = call.parameters["newMsg"]
        val counterParty = NetworkParty(proxy).getPartyList().random()
        if (msg != null) {
          val msgState: MsgState = MsgState(msg, counterParty as Party);
          proxy.startTrackedFlowDynamic(MsgFlowInitiator::class.java, msgState)
        }
      } catch (b: BaseApplicationResponse.ResponseAlreadySentException) {}
    }
    
    get("/health-check") {
      try {
        println("Check successful!")
        call.respond("Check successful!")
      } catch(B : BaseApplicationResponse.ResponseAlreadySentException) {}
    }
    }
  }

