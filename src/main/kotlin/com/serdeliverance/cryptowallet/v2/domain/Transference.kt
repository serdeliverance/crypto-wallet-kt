package com.serdeliverance.cryptowallet.v2.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Transference(val issuerId: Int, val receiverId: Int, val amount: BigDecimal, val withdrawalTransactionId: Long, val depositTransactionId: Long, val date: LocalDateTime)