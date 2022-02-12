package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.records.MemosRecord
import java.time.LocalDateTime

//class Memo(id: EntityID<UUID>) : UUIDEntity(id) {
//
//    companion object : UUIDEntityClass<Memo>(Memos)
//
//    var user by User referencedOn Memos.userId
//
//    var title by Memos.title
//    var body by Memos.body
//
//    var createdAt by Memos.createdAt
//    var updatedAt by Memos.updatedAt
//    var deletedAt by Memos.deletedAt
//}

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
