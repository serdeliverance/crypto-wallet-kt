package com.serdeliverance.cryptowallet.application.port.out

interface UpdateBalancePort {
    fun update(userId: Int, cryptocurrencyId: Int, amount: Any): Long
}
