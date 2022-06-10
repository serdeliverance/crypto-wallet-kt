package com.serdeliverance.cryptowallet.application.port.out

import com.serdeliverance.cryptowallet.v2.domain.Balance

interface LoadBalancePort {

    fun getBalanceByCurrency(userId: Int, currencyId: Int): Balance
}
