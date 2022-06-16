package com.serdeliverance.cryptowallet.v2.domain.exception

class InsufficientFundsException(message: String = "Insufficient funds"): RuntimeException(message)