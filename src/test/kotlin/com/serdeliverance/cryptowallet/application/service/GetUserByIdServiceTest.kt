package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.v2.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.spekframework.spek2.Spek

object GetUserByIdServiceTest : Spek({

    val userRepository = mock<UserRepositoryPort>()
    val subject = GetUserByIdService(userRepository)

    test("get user by id") {
        whenever(userRepository.find(2)).thenReturn(User(2, "pepe", "asd123as", "pepe@gmail.com"))
        val result = subject.getUserById(2)
        assertEquals(User(2, "pepe", "asd123as", "pepe@gmail.com"), result)
    }

    test("get user not find user") {
        whenever(userRepository.find(2)).thenReturn(null)
        val result = subject.getUserById(2)
        assertEquals(null, result)
    }
})