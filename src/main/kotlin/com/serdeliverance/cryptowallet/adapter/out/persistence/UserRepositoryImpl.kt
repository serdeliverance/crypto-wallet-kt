package com.serdeliverance.cryptowallet.adapter.out.persistence

import com.serdeliverance.cryptowallet.application.port.out.UserRepositoryPort
import com.serdeliverance.cryptowallet.v2.domain.User
import com.serdeliverance.cryptowallet.util.OptionExtension.value
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.ResultSet

class UserRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : UserRepositoryPort {

    override fun find(id: Int): User? {
        return jdbcTemplate.query<User>(
            "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE ENABLED = TRUE AND ID = ?",
            { rs: ResultSet, rowNum: Int ->
                User(
                    rs.getInt("ID"),
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("EMAIL")
                )
            }, id
        ).stream().findFirst().value
    }

}