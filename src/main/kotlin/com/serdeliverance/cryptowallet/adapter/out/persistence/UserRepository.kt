package com.serdeliverance.cryptowallet.adapter.out.persistence

import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.domain.User
import com.serdeliverance.cryptowallet.util.OptionExtension.value

// TODO remove repo val once repository code were completely migrated
class UserRepository(private val repo: com.serdeliverance.cryptowallet.repositories.UserRepository) : UserRepositoryPort {

    override fun find(id: Int): User? {
        return repo.find(id).value
    }

}