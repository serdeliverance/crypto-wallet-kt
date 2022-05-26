package com.serdeliverance.cryptowallet.application.port.`in`

import com.serdeliverance.cryptowallet.v2.domain.User

interface GetUserByIdUseCase {
    fun getUserById(id: Int): User?
}