package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.WithdrawUseCase
import com.serdeliverance.cryptowallet.application.port.out.LoadBalancePort
import com.serdeliverance.cryptowallet.application.port.out.UpdateBalancePort
import com.serdeliverance.cryptowallet.v2.domain.OperationType
import com.serdeliverance.cryptowallet.v2.domain.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

class WithdrawService(val loadBalancePort: LoadBalancePort, val updateBalancePort: UpdateBalancePort) : WithdrawUseCase {
    override fun withdraw(userId: Int, cryptocurrencyId: Int, amount: BigDecimal): Transaction {
        val balance = loadBalancePort.getBalanceByCurrency(userId, cryptocurrencyId)
        val newAmount = balance.amount - amount
        val transactionId = updateBalancePort.update(userId, cryptocurrencyId, newAmount)
        return Transaction(transactionId, userId, cryptocurrencyId, newAmount, OperationType.WITHDRAW, LocalDateTime.now())
    }
}