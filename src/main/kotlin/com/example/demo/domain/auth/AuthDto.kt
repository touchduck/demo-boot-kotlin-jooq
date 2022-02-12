package com.example.demo.domain.auth

import com.example.demo.infra.hawaii.tables.records.UsersRecord
import java.time.LocalDateTime

data class AuthDto(
    val id: String,
    val username: String,
    val nickname: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
) {
    companion object {

        fun toMapping(user: UsersRecord): AuthDto {

            return AuthDto(
                user.id.toString(),
                user.username,
                user.nickname,
                user.createdAt,
                user.updatedAt,
                user.deletedAt
            )
        }
    }
}
