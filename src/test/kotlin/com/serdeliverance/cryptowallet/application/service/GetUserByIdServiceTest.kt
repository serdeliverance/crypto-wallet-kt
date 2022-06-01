package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.v2.domain.User
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek

object GetUserByIdServiceTest : Spek({

    val userRepository = mockk<UserRepositoryPort>()
    val subject = GetUserByIdService(userRepository)

    test("get user by id") {
        every { userRepository.find(2) } returns User(2, "pepe", "asd123as", "pepe@gmail.com")
        val result = subject.getUserById(2)
        assertEquals(User(2, "pepe", "asd123as", "pepe@gmail.com"), result)
    }

    test("get user not find user") {
        every { userRepository.find(2) } returns null
        val result = subject.getUserById(2)
        assertEquals(null, result)
    }
})