package com.serdeliverance.cryptowallet.application.port.`in`

import com.serdeliverance.cryptowallet.v2.domain.Transaction
import java.math.BigDecimal

interface WithDrawalUseCase {
    fun withdraw(id: Int, amount: BigDecimal): Transaction
}
