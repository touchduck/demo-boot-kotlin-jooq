package com.example.demo.app_service.memo

import com.example.demo.domain.memo.MemoParam
import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface MemoService {

    // メモーの作成
    suspend fun create(userId: UUID, memoParam: MemoParam): Mono<MemosRecord>

    // メモーの一覧
    suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<MemosRecord>>

    // メモーの詳細
    suspend fun getDetail(userId: UUID, memoId: UUID): Mono<MemosRecord>

    // メモーの更新
    suspend fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): Mono<MemosRecord>

    // メモーの削除
    suspend fun delete(userId: UUID, memoId: UUID): Mono<Int>
}
