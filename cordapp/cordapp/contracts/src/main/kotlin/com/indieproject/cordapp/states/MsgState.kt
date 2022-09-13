package com.indieproject.cordapp.states

import com.indieproject.cordapp.contracts.MsgContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party

@BelongsToContract(MsgContract::class)
data class MsgState(private val msg: String?,
                    private val counterparty: Party,
                    override val participants: List<AbstractParty> = listOf(counterparty)
) : ContractState {
  
  public fun getMsg(): String? { return this.msg }
  public fun getCounterParty(): Party { return this.counterparty }
}
