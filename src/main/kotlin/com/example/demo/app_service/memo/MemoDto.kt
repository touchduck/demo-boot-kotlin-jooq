package com.example.demo.app_service.memo

import com.example.demo.infra.hawaii.tables.records.MemoRecord
import java.time.LocalDateTime

data class MemoDto(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
) {
    companion object {

        fun create(memoRecord: MemoRecord): MemoDto {
            return MemoDto(
                id = memoRecord.id.toString(),
                title = memoRecord.title,
                body = memoRecord.body,
                createdAt = memoRecord.createdAt,
                updatedAt = memoRecord.updatedAt,
                deletedAt = memoRecord.deletedAt
            )
        }

    }
}
