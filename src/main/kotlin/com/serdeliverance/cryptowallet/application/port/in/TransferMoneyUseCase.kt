package com.serdeliverance.cryptowallet.application.port.`in`

import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.v2.domain.User
import java.math.BigDecimal

interface TransferMoneyUseCase {

    // TODO analizar si no conviene que el retorno de esta operacion sea un Result en lugar de Unit, definiendo a Result como
    // data class TransferResult(issuerId: Int, receiverId: Int, amount: Bigdecimal, withdrawalTransacitonId: Long depositTransactionId: Long, date: LocalDateTime)
    // EN ese caso, corregir el blog
    fun transfer(issuer: User, receiver: User, cryptocurrency: Cryptocurrency, amount: BigDecimal): Unit
}