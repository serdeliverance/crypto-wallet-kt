package com.serdeliverance.cryptowallet.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Portfolio(
    val user: User,
    val currencies: List<CurrencyTotal>,
    val totalInUSD: BigDecimal,
    val date: LocalDateTime
)