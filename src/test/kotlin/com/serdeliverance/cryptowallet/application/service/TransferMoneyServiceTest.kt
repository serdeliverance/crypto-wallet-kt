package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.DepositUseCase
import com.serdeliverance.cryptowallet.application.port.`in`.WithdrawUseCase
import com.serdeliverance.cryptowallet.application.port.out.LoadBalancePort
import com.serdeliverance.cryptowallet.domain.Cryptocurrency
import com.serdeliverance.cryptowallet.v2.domain.Balance
import com.serdeliverance.cryptowallet.v2.domain.Transaction
import com.serdeliverance.cryptowallet.v2.domain.Transference
import com.serdeliverance.cryptowallet.v2.domain.OperationType.*
import com.serdeliverance.cryptowallet.v2.domain.User
import com.serdeliverance.cryptowallet.v2.domain.exception.InsufficientFundsException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.spekframework.spek2.Spek
import java.math.BigDecimal
import java.time.LocalDateTime

object TransferMoneyServiceTest : Spek({

    val loadBalancePort = mock<LoadBalancePort>()
    val withdrawalUserCase = mock<WithdrawUseCase>()
    val depositUseCase = mock<DepositUseCase>()

    val subject = TransferMoneyService(loadBalancePort, withdrawalUserCase, depositUseCase)

    val issuer = User(1, "pedro", "pass1234", "pedro@gmail.com")
    val receiver = User(2, "joaco", "pass1234", "joaco@outlook.com")

    test("successful transaction") {

        val withdrawalResult = Transaction(1, 1, 1, BigDecimal(5), WITHDRAW, LocalDateTime.now())
        val depositResult = Transaction(2, 2, 2, BigDecimal(5), DEPOSIT, LocalDateTime.now())

        whenever(loadBalancePort.getBalanceByCurrency(1, 1)).thenReturn(Balance(1, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal.TEN))
        whenever(withdrawalUserCase.withdraw(1, BigDecimal(5))).thenReturn(withdrawalResult)
        whenever(depositUseCase.deposit(2, BigDecimal(5))).thenReturn(depositResult)

        val result = subject.transfer(issuer, receiver, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal(5))

        assertEquals(Transference(1, 2, BigDecimal(5), 1, 2, depositResult.transactionDate), result)
    }

    test("failed transaction by insufficient funds") {
        val issuer = User(1, "pedro", "pass1234", "pedro@gmail.com")
        val receiver = User(2, "joaco", "pass1234", "joaco@outlook.com")

        whenever(loadBalancePort.getBalanceByCurrency(1, 1)).thenReturn(Balance(1, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal.ONE))

        assertThrows<InsufficientFundsException> {
            subject.transfer(issuer, receiver, Cryptocurrency(1, "bitcoin", "BTC"), BigDecimal(5))
        }
    }
})