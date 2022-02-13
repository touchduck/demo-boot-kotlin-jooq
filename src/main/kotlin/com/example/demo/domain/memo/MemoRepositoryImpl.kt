package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.tables.Memos
import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import com.example.demo.util.TimeUtil
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class MemoRepositoryImpl(
    private val dsl: DSLContext,
) : MemoRepository {

    override fun count(userId: UUID): Long {
        return dsl.selectCount()
            .from(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .fetchOne().value1().toLong()
    }

    override fun save(userId: UUID, memosRecord: MemosRecord): MemosRecord {

        memosRecord.id = UUID.randomUUID()
        memosRecord.userId = userId

        val now = TimeUtil.getDateTimeNow()

        memosRecord.createdAt = now
        memosRecord.updatedAt = now

        return dsl.insertInto(Memos.MEMOS)
            .set(memosRecord)
            .returning()
            .fetchOne()
    }

    override fun findAll(userId: UUID): List<MemosRecord> {
        return dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .orderBy(Memos.MEMOS.CREATED_AT.desc())
            .fetch()
    }

    override fun findAll(userId: UUID, size: Int, offset: Long): Pagination<MemosRecord> {

        val cnt = count(userId)

        val ret = dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .orderBy(Memos.MEMOS.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, cnt, ret)
    }

    override fun findById(userId: UUID, memoId: UUID): MemosRecord? {
        return dsl.selectFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .orderBy(Memos.MEMOS.CREATED_AT.desc())
            .fetchOne()
    }

    override fun updateById(userId: UUID, memoId: UUID, memosRecord: MemosRecord): MemosRecord {
        memosRecord.updatedAt = TimeUtil.getDateTimeNow()
        return dsl.update(Memos.MEMOS)
            .set(memosRecord)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .returning().fetchOne()
    }

    override fun deleteById(userId: UUID, memoId: UUID): Int {
        return dsl.deleteFrom(Memos.MEMOS)
            .where(Memos.MEMOS.DELETED_AT.isNull)
            .and(Memos.MEMOS.ID.eq(memoId))
            .and(Memos.MEMOS.USER_ID.eq(userId))
            .execute()
    }

}
