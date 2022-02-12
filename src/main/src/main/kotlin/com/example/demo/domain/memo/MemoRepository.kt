package com.example.demo.domain.memo

import com.example.demo.domain.user.User
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface MemoRepository {
    fun create(ownerUser: User, memoParam: MemoParam): Mono<Memo>
    fun findAll(ownerUserId: UUID, paginationParam: PaginationParam): Mono<Pagination<Memo>>
    fun findById(ownerUserId: UUID, memoId: UUID): Mono<Memo>
    fun updateById(ownerUserId: UUID, memoId: UUID, memoParam: MemoParam): Mono<Memo>
    fun deleteById(ownerUserId: UUID, memoId: UUID): Mono<Memo>
}
