package com.serdeliverance.cryptowallet.application

import com.serdeliverance.cryptowallet.application.port.`in`.GetUserByIdUseCase
import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.domain.User

class GetUserByIdService(private val userRepository: UserRepositoryPort) : GetUserByIdUseCase {

    override fun getUserById(id: Int): User? {
        return userRepository.find(id)
    }
}