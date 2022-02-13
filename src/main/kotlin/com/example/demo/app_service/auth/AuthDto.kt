package com.example.demo.app_service.auth

import com.example.demo.app_service.memo.MemoDto
import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import java.time.LocalDateTime

data class AuthDto(
    val id: String,
    val username: String,
    val nickname: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
)

fun UsersRecord.toAuthDto() = AuthDto(
    id = id.toString(),
    username = username,
    nickname = nickname,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt
)
