package com.example.demo.dto

import com.example.demo.domain.memo.Memo
import java.time.LocalDateTime

data class MemoDTO(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
) {
    companion object {

        fun toMapping(memo: Memo): MemoDTO {

            return MemoDTO(
                memo.id.toString(),
                memo.title,
                memo.body,
                memo.createdAt,
                memo.updatedAt,
                memo.deletedAt
            )
        }
    }
}
