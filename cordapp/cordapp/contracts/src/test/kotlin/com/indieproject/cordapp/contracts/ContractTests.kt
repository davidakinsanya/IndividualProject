package com.indieproject.cordapp.contracts

import net.corda.core.identity.CordaX500Name
import net.corda.testing.core.TestIdentity
import net.corda.testing.node.MockServices
import net.corda.testing.node.ledger
import org.junit.Test
import com.indieproject.cordapp.states.MsgState

class ContractTests {
    private val ledgerServices: MockServices = MockServices(listOf("com.template"))
    var alice = TestIdentity(CordaX500Name("Alice", "TestLand", "US"))
    var bob = TestIdentity(CordaX500Name("Alice", "TestLand", "US"))
    /*
    @Test
    fun dummytest() {
        val state = MsgState("Hello-World", alice.party, listOf(alice.party, bob.party))
        ledgerServices.ledger {
            // Should fail bid price is equal to previous highest bid
            transaction {
                //failing transaction
                input(MsgContract.ID, state)
                output(MsgContract.ID, state)
                command(alice.publicKey, MsgContract.Commands.Create())
                fails()
            }
            //pass
            transaction {
                //passing transaction
                output(MsgContract.ID, state)
                command(alice.publicKey, MsgContract.Commands.Create())
                verifies()
            }
        }
    } */
}