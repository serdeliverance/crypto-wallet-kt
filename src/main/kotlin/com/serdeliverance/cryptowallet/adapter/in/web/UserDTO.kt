package com.serdeliverance.cryptowallet.adapter.`in`.web

import com.serdeliverance.cryptowallet.v2.domain.User
import java.util.*

data class UserDTO(val id: Int?, val username: String, val email: String) {

    companion object {
        fun User.toDTO() = UserDTO(
            id = this.id,
            username = this.username,
            email = this.email
        )
    }
}
