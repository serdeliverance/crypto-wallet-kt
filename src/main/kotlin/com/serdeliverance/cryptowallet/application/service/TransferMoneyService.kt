package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.DepositUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.TransferMoneyUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.WithDrawalUseCase
import com.serdeliverance.cryptowallet.application.port.out.LoadBalancePort
import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.v2.domain.User
import java.math.BigDecimal

class TransferMoneyService(val loadBalancePort: LoadBalancePort, val withdrawalUserCase: WithDrawalUseCase, val depositUseCase: DepositUseCase) : TransferMoneyUseCase {

    override fun transfer(issuer: User, receiver: User, cryptocurrency: Cryptocurrency, amount: BigDecimal) {
        val issuerBalance = loadBalancePort.getBalanceByCurrency(issuer.id, cryptocurrency.id)
        if (issuerBalance.amount >= amount) {
            withdrawalUserCase.withdraw(issuer.id, amount)
            depositUseCase.deposit(receiver.id, amount)
        }
    }
}