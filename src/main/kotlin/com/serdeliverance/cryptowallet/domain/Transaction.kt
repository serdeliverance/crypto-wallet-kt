package com.serdeliverance.cryptowallet.domain

import java.math.BigDecimal

data class Transaction(
    val id: Long,
    val userId: Int,
    val cryptoCurrencyId: Int,
    val amount: BigDecimal,
    val operationType: OperationType,
    val transactionDate: String
)