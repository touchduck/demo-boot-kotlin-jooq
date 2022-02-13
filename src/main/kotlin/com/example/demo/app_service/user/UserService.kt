package com.example.demo.app_service.user

import com.example.demo.infra.hawaii.tables.records.UserRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import java.util.*

interface UserService {

    // ユーザーの作成
    fun create(userId: UUID, userParam: UserParam): UserRecord?

    // ユーザーの一覧
    fun getList(userId: UUID, paginationParam: PaginationParam): Pagination<UserRecord>

    // ユーザーの詳細
    fun getDetail(userId: UUID): UserRecord?

    // ユーザーの更新
    fun update(userId: UUID, userParam: UserParam): UserRecord?

    // ユーザーの削除
    fun delete(userId: UUID): Int

}
