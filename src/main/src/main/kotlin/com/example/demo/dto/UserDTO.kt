package com.example.demo.dto

import com.example.demo.domain.user.User
import java.time.LocalDate
import java.time.LocalDateTime

data class UserDTO(
    val id: String,
    val username: String,
    val nickname: String,
    val birthday: LocalDate?,
    val sex: Short?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
) {
    companion object {

        fun toMapping(user: User): UserDTO {

            return UserDTO(
                user.id.toString(),
                user.username,
                user.nickname,
                user.birthday,
                user.sex,
                user.createdAt,
                user.updatedAt,
                user.deletedAt
            )
        }
    }
}
