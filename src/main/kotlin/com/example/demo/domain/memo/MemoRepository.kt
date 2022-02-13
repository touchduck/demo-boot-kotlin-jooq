package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import reactor.core.publisher.Mono
import java.util.*

interface MemoRepository {

    fun save(userId: UUID, memosRecord: MemosRecord): Mono<MemosRecord>

    fun findAll(userId: UUID): Mono<List<MemosRecord>>

    fun findAll(userId: UUID, size: Int, offset: Long): Mono<Pagination<MemosRecord>>

    fun findById(userId: UUID, memoId: UUID): Mono<MemosRecord>

    fun updateById(userId: UUID, memoId: UUID, memosRecord: MemosRecord): Mono<MemosRecord>

    fun deleteById(userId: UUID, memoId: UUID): Mono<Int>
}
