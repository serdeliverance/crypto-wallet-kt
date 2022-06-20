package com.serdeliverance.cryptowallet.application.port.`in`

import com.serdeliverance.cryptowallet.v2.domain.Transaction
import java.math.BigDecimal

interface WithdrawUseCase {
    fun withdraw(id: Int, cryptocurrencyId: Int, amount: BigDecimal): Transaction
}
