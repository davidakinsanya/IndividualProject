package com.indieproject.cordapp.webserver.party

import net.corda.core.identity.CordaX500Name
import net.corda.core.identity.Party
import net.corda.core.messaging.CordaRPCOps

class NetworkParty constructor (private val proxy: CordaRPCOps) {
  
  private val name1 = CordaX500Name.parse("O=PartyA,L=London,C=GB")
  private val name2 = CordaX500Name.parse("O=PartyB,L=New York,C=US")
  
  private val partyA = proxy.wellKnownPartyFromX500Name(name1)
  private val partyB = proxy.wellKnownPartyFromX500Name(name2)
  
  fun getPartyList(): List<Party?> {
    return listOf<Party?>(this.partyA, this.partyB)
  }
}