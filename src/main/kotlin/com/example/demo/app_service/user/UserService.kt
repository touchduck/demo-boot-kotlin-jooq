package com.example.demo.app_service.user

import com.example.demo.infra.hawaii.tables.records.UserRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface UserService {

    // ユーザーの作成
    suspend fun create(userId: UUID, userParam: UserParam): Mono<UserRecord>

    // ユーザーの一覧
    suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<UserRecord>>

    // ユーザーの詳細
    suspend fun getDetail(userId: UUID): Mono<UserRecord>

    // ユーザーの更新
    suspend fun update(userId: UUID, userParam: UserParam): Mono<UserRecord>

    // ユーザーの削除
    suspend fun delete(userId: UUID): Mono<Int>

}
