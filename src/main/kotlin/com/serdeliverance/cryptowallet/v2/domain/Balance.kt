package com.serdeliverance.cryptowallet.v2.domain

import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import java.math.BigDecimal

data class Balance(val userId: Int, val cryptocurrency: Cryptocurrency, val amount: BigDecimal)
