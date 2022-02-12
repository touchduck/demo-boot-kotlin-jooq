package com.example.demo.domain.memo

import com.example.demo.domain.user.User
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import com.example.demo.util.TimeUtil
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Transactional
@Repository
class MemoRepositoryImpl : MemoRepository {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(ownerUser: User, memoParam: MemoParam): Mono<Memo> {

        return Memo.new {
            user = ownerUser
            title = memoParam.title
            body = memoParam.body

        }.toMono()
    }

    override fun findAll(ownerUserId: UUID, paginationParam: PaginationParam): Mono<Pagination<Memo>> {

        val query = Memos.select { (Memos.userId eq ownerUserId) and (Memos.deletedAt.isNull()) }
        val total = query.count()

        val memos = query.orderBy(Memos.updatedAt to SortOrder.DESC)
            .limit(paginationParam.size, paginationParam.offset)
            .toList().map { Memo.wrapRow(it) }

        return Pagination(paginationParam.page, paginationParam.size, total, memos).toMono()
    }

    override fun findById(ownerUserId: UUID, memoId: UUID): Mono<Memo> {

        return Memo.find {
            Memos.id.eq(memoId)
                .and(Memos.userId.eq(ownerUserId))
                .and(Memos.deletedAt.isNull())

        }.singleOrNull().toMono()
    }

    override fun updateById(ownerUserId: UUID, memoId: UUID, memoParam: MemoParam): Mono<Memo> {

        Memo.find {
            Memos.id.eq(memoId)
                .and(Memos.userId.eq(ownerUserId))
                .and(Memos.deletedAt.isNull())

        }.singleOrNull {
            it.title = memoParam.title
            it.body = memoParam.body
            it.updatedAt = TimeUtil.getDateTimeNow()
            return it.toMono()
        }

        return Mono.empty()
    }

    override fun deleteById(ownerUserId: UUID, memoId: UUID): Mono<Memo> {

        Memo.find {
            Memos.id.eq(memoId)
                .and(Memos.userId.eq(ownerUserId))
                .and(Memos.deletedAt.isNull())

        }.singleOrNull {
            it.deletedAt = TimeUtil.getDateTimeNow()
            return it.toMono()
        }

        return Mono.empty()
    }

}
