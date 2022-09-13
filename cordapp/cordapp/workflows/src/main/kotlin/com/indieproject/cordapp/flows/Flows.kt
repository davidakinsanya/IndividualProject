package com.indieproject.cordapp.flows

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.flows.*
import net.corda.core.utilities.ProgressTracker
import net.corda.core.flows.FinalityFlow
import net.corda.core.flows.CollectSignaturesFlow
import net.corda.core.transactions.SignedTransaction
import java.util.stream.Collectors
import net.corda.core.flows.FlowSession
import net.corda.core.identity.Party
import com.indieproject.cordapp.contracts.MsgContract
import net.corda.core.transactions.TransactionBuilder
import com.indieproject.cordapp.states.MsgState
import net.corda.core.contracts.requireThat
import net.corda.core.identity.AbstractParty


// *********
// * Flows *
// *********
@InitiatingFlow
@StartableByRPC
class MsgFlowInitiator(private val state: MsgState) : FlowLogic<SignedTransaction>() {
  override val progressTracker = ProgressTracker()
  
  @Suspendable
  override fun call(): SignedTransaction {
    
    val msg = this.state.getMsg()
    val notary = serviceHub.networkMapCache.notaryIdentities[0]
    val builder = TransactionBuilder(notary)
      .addCommand(MsgContract.Commands.Create(), this.state.getCounterParty().owningKey)
      .addOutputState(this.state, MsgContract.ID)
    
      builder.verify(serviceHub)
      val ptx = serviceHub.signInitialTransaction(builder)
    
    
    // Step 6. Collect the other party's signature using the SignTransactionFlow.
    val otherParties: MutableList<Party> = this.state.participants.stream().map { el: AbstractParty? -> el as Party? }.collect(Collectors.toList())
    otherParties.remove(ourIdentity)
    val sessions = otherParties.stream().map { el: Party? -> initiateFlow(el!!) }.collect(Collectors.toList())
    
    val stx = subFlow(CollectSignaturesFlow(ptx, sessions))
    
    // Step 7. Assuming no exceptions, we can now finalise the transaction
    return subFlow<SignedTransaction>(FinalityFlow(stx, sessions))
  }
}



@InitiatedBy(MsgFlowInitiator::class)
class MsgFlowResponder(val counterpartySession: FlowSession) : FlowLogic<SignedTransaction>() {
  @Suspendable
  override fun call(): SignedTransaction {
    val signTransactionFlow = object : SignTransactionFlow(counterpartySession) {
      override fun checkTransaction(stx: SignedTransaction) = requireThat {
        //Addition checks
      }
    }
    val txId = subFlow(signTransactionFlow).id
    return subFlow(ReceiveFinalityFlow(counterpartySession, expectedTxId = txId))
  }
}

