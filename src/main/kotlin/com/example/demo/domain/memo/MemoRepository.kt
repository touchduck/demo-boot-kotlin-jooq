package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.records.MemoRecord
import com.example.demo.util.Pagination
import java.util.*

interface MemoRepository {

    fun count(userId: UUID): Long

    fun save(userId: UUID, memoRecord: MemoRecord): MemoRecord

    fun findAll(userId: UUID): List<MemoRecord>

    fun findAll(userId: UUID, size: Int, offset: Long): Pagination<MemoRecord>

    fun findById(userId: UUID, memoId: UUID): MemoRecord?

    fun updateById(userId: UUID, memoId: UUID, MemoRecord: MemoRecord): MemoRecord

    fun deleteById(userId: UUID, memoId: UUID): Int
}
