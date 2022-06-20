package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.DepositUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.TransferMoneyUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.WithdrawUseCase
import com.serdeliverance.cryptowallet.application.port.out.LoadBalancePort
import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.v2.domain.Transference
import com.serdeliverance.cryptowallet.v2.domain.User
import com.serdeliverance.cryptowallet.v2.domain.exception.InsufficientFundsException
import java.math.BigDecimal

class TransferMoneyService(val loadBalancePort: LoadBalancePort, val withdrawalUserCase: WithdrawUseCase, val depositUseCase: DepositUseCase) : TransferMoneyUseCase {

    override fun transfer(issuer: User, receiver: User, cryptocurrency: Cryptocurrency, amount: BigDecimal): Transference {
        val issuerBalance = loadBalancePort.getBalanceByCurrency(issuer.id, cryptocurrency.id)

        if (issuerBalance.amount < amount)
            throw InsufficientFundsException()

        val withdrawTransactionResult = withdrawalUserCase.withdraw(issuer.id, cryptocurrency.id, amount)
        val depositTransactionResult = depositUseCase.deposit(receiver.id, cryptocurrency.id, amount)

        return Transference(issuer.id, receiver.id, amount, withdrawTransactionResult.id, depositTransactionResult.id, depositTransactionResult.transactionDate)
    }
}