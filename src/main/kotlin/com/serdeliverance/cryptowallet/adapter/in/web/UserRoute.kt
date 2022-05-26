package com.serdeliverance.cryptowallet.adapter.`in`.web

import com.serdeliverance.cryptowallet.application.port.`in`.GetUserByIdUseCase
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v2/users")
class UserRoute(private val getUserByIdUseCase: GetUserByIdUseCase) {

    private val LOGGER = LoggerFactory.getLogger(UserRoute::class.java)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): UserDTO {
        LOGGER.info("Getting user with id: {}", id)
        val user = getUserByIdUseCase.getUserById(id)
        // TODO map user to userDTO (look a way to that in kotlin
        return UserDTO(1, "el-noba", "tamochelo@gmail.com")
    }
}