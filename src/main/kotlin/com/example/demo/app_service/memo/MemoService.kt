package com.example.demo.app_service.memo

import com.example.demo.infra.hawaii.tables.records.MemoRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import java.util.*

interface MemoService {

    // メモーの作成
    fun create(userId: UUID, memoParam: MemoParam): MemoRecord?

    // メモーの一覧
    fun getList(userId: UUID, paginationParam: PaginationParam): Pagination<MemoRecord>

    // メモーの詳細
    fun getDetail(userId: UUID, memoId: UUID): MemoRecord?

    // メモーの更新
    fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): MemoRecord?

    // メモーの削除
    fun delete(userId: UUID, memoId: UUID): Int
}
