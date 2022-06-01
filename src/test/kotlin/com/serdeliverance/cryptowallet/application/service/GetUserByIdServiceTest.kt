package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.v2.domain.User
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetUserByIdServiceTest : WordSpec({

    val userRepository = mockk<UserRepositoryPort>()
    val subject = GetUserByIdService(userRepository)

    "GetUserByIdService" When {
        "founds user" Should {
            every { userRepository.find(2) } returns User(2, "pepe", "asd123as", "pepe@gmail.com")
            val result = subject.getUserById(2)

            result shouldBe User(2, "pepe", "asd123as", "pepe@gmail.com")
        }

        "not found user" Should {
            every { userRepository.find(2) } returns null
            val result = subject.getUserById(2)

            result shouldBe null
        }
    }
})