package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import reactor.core.publisher.Mono
import java.util.*

interface MemoRepository {

    fun create(userId: UUID, memoParam: MemoParam): Mono<MemosRecord>

    fun findAll(userId: UUID): Mono<List<MemosRecord>>

    fun pagination(userId: UUID, size: Int, offset: Long): Mono<Pagination<MemosRecord>>

    fun findById(userId: UUID, memoId: UUID): Mono<MemosRecord>

    fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): Mono<MemosRecord>

    fun deleteById(userId: UUID, memoId: UUID): Mono<Int>
}
