package com.example.demo.app_service.memo

import com.example.demo.infra.hawaii.tables.records.MemoRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface MemoService {

    // メモーの作成
    suspend fun create(userId: UUID, memoParam: MemoParam): Mono<MemoRecord>

    // メモーの一覧
    suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<MemoRecord>>

    // メモーの詳細
    suspend fun getDetail(userId: UUID, memoId: UUID): Mono<MemoRecord>

    // メモーの更新
    suspend fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): Mono<MemoRecord>

    // メモーの削除
    suspend fun delete(userId: UUID, memoId: UUID): Mono<Int>
}
