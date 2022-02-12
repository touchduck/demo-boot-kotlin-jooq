package com.example.demo.app_service.user

import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface UserService {

    // ユーザーの作成
    suspend fun create(userId: UUID, userParam: UserParam): Mono<UsersRecord>

    // ユーザーの一覧
    suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<UsersRecord>>

    // ユーザーの詳細
    suspend fun getDetail(userId: UUID): Mono<UsersRecord>

    // ユーザーの更新
    suspend fun update(userId: UUID, userParam: UserParam): Mono<UsersRecord>

    // ユーザーの削除
    suspend fun delete(userId: UUID): Mono<Int>

}
