package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.DepositUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.WithDrawalUseCase
import com.serdeliverance.cryptowallet.application.port.out.LoadBalancePort
import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.domain.OperationType
import com.serdeliverance.cryptowallet.v2.domain.Balance
import com.serdeliverance.cryptowallet.v2.domain.Transaction
import com.serdeliverance.cryptowallet.v2.domain.Transference
import com.serdeliverance.cryptowallet.v2.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.spekframework.spek2.Spek
import java.math.BigDecimal
import java.time.LocalDateTime

object TransferMoneyServiceTest : Spek({

    val loadBalancePort = mock<LoadBalancePort>()
    val withdrawalUserCase = mock<WithDrawalUseCase>()
    val depositUseCase = mock<DepositUseCase>()

    val subject = TransferMoneyService(loadBalancePort, withdrawalUserCase, depositUseCase)

    test("successful transaction") {
        val issuer = User(1, "pedro", "pass1234", "pedro@gmail.com")
        val receiver = User(2, "joaco", "pass1234", "joaco@outlook.com")

        val withdrawalResult = Transaction(1, 1, 1, BigDecimal(5), OperationType.TRANSFERENCE, LocalDateTime.now())
        val depositResult = Transaction(2, 2, 2, BigDecimal(5), OperationType.TRANSFERENCE, LocalDateTime.now())

        whenever(loadBalancePort.getBalanceByCurrency(1, 1)).thenReturn(Balance(1, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal.TEN))
        whenever(withdrawalUserCase.withdraw(1, BigDecimal(5))).thenReturn(withdrawalResult)
        whenever(depositUseCase.deposit(2, BigDecimal(5))).thenReturn(depositResult)

        val result = subject.transfer(issuer, receiver, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal(5))

        assertEquals(Transference(1, 2, BigDecimal(5), 1, 2, depositResult.transactionDate), result)
    }

    test("failed transaction by insufficient funds") {
        // TODO
    }
})