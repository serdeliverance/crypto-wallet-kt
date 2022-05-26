package com.serdeliverance.cryptowallet.application.port.out

import com.serdeliverance.cryptowallet.domain.User

interface UserRepositoryPort {

    fun find(id: Int): User?
}