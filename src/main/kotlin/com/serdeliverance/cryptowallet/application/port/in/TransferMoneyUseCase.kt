package com.serdeliverance.cryptowallet.application.port.`in`

import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.v2.domain.Transference
import com.serdeliverance.cryptowallet.v2.domain.User
import java.math.BigDecimal

interface TransferMoneyUseCase {

    fun transfer(issuer: User, receiver: User, cryptocurrency: Cryptocurrency, amount: BigDecimal): Transference
}