package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.DepositUseCase
import com.serdeliverance.cryptowallet.v2.domain.Transaction
import java.math.BigDecimal

class DepositService : DepositUseCase {
    override fun deposit(id: Int, amount: BigDecimal): Transaction {
        TODO("Not yet implemented")
    }
}