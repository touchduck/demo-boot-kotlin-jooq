package com.example.demo.app_service.memo

import com.example.demo.infra.hawaii.tables.records.MemosRecord
import java.time.LocalDateTime

class MemoDto(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
)

fun MemosRecord.toDto() = MemoDto(
    id = id.toString(),
    title = title,
    body = body,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt
)
