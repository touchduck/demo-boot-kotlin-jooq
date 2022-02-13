package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import java.util.*

interface MemoRepository {

    fun count(userId: UUID): Long

    fun save(userId: UUID, memosRecord: MemosRecord): MemosRecord

    fun findAll(userId: UUID): List<MemosRecord>

    fun findAll(userId: UUID, size: Int, offset: Long): Pagination<MemosRecord>

    fun findById(userId: UUID, memoId: UUID): MemosRecord?

    fun updateById(userId: UUID, memoId: UUID, memosRecord: MemosRecord): MemosRecord

    fun deleteById(userId: UUID, memoId: UUID): Int
}
