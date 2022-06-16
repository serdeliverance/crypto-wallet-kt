package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.WithdrawalUseCase
import com.serdeliverance.cryptowallet.v2.domain.Transaction
import java.math.BigDecimal

class WithdrawalService : WithdrawalUseCase {
    override fun withdraw(id: Int, amount: BigDecimal): Transaction {
        TODO("Not yet implemented")
    }
}