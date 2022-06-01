package com.serdeliverance.cryptowallet.application.service

import com.serdeliverance.cryptowallet.application.port.`in`.GetUserByIdUseCase
import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.v2.domain.User
import org.springframework.stereotype.Service

@Service
class GetUserByIdService(private val userRepository: UserRepositoryPort) : GetUserByIdUseCase {

    override fun getUserById(id: Int): User? {
        return userRepository.find(id)
    }
}