package com.indieproject.cordapp.webserver.utils

import net.corda.client.rpc.CordaRPCClient
import net.corda.client.rpc.CordaRPCConnection
import net.corda.core.utilities.NetworkHostAndPort



fun connectToNode(): CordaRPCConnection {
  val host: String = "67.205.179.38"
  val rpcPort: Int = 10006
  val username: String = "user1"
  val password: String = "test"
  
  val rpcAddress = NetworkHostAndPort(host, rpcPort)
  val rpcClient = CordaRPCClient(rpcAddress)
  
  return rpcClient.start(username, password)
}
