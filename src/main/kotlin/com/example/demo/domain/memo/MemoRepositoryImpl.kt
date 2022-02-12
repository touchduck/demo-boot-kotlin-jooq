package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.Memos
import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Repository
class MemoRepositoryImpl(
    private val dsl: DSLContext,
) : MemoRepository {

    override fun findAll(userId: UUID): Mono<List<MemosRecord>> {

        val ret = dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .orderBy(Memos.MEMOS.CREATED_AT.desc()).fetch()
        return ret.toMono()
    }

    override fun pagination(userId: UUID, size: Int, offset: Long): Mono<Pagination<MemosRecord>> {

        val count = dsl.selectCount()
            .from(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .fetchOne().value1().toLong()

        val ret = dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .orderBy(Memos.MEMOS.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, count, ret).toMono()
    }

    override fun findById(userId: UUID, memoId: UUID): Mono<MemosRecord> {

        val ret = dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .orderBy(Memos.MEMOS.CREATED_AT.desc()).fetchOne()
        return ret.toMono()
    }

    override fun update(userId: UUID, memoId: UUID, memosRecord: MemosRecord): Mono<Int> {

        val ret = dsl.update(Memos.MEMOS)
            .set(Memos.MEMOS.TITLE, memosRecord.title)
            .set(Memos.MEMOS.BODY, memosRecord.body)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .execute()

        return ret.toMono()
    }

    override fun deleteById(userId: UUID, memoId: UUID): Mono<Int> {

        val ret = dsl.deleteFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .execute()

        return ret.toMono()
    }

}
