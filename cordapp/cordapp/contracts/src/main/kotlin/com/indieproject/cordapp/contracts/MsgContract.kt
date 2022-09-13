package com.indieproject.cordapp.contracts

import com.indieproject.cordapp.states.MsgState
import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.contracts.requireSingleCommand
import net.corda.core.transactions.LedgerTransaction
import net.corda.core.contracts.requireThat

class MsgContract : Contract {
    companion object {
        const val ID = "com.indieproject.cordapp.contracts.MsgContract"
    }
    
    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<Commands.Create>()
        val output = tx.outputsOfType<MsgState>().first()
        when (command.value) {
            is Commands.Create -> requireThat {
                // "No inputs should be consumed when sending the Hello-World message.".using(tx.inputStates.isEmpty())
                // "The message must be Hello-World".using(output.getMsg() == "Hello-World")
                
                // TODO: Create conditions of the MsgContract
            }
        }
    }
    
    interface Commands : CommandData {
        class Create : Commands
    }
}