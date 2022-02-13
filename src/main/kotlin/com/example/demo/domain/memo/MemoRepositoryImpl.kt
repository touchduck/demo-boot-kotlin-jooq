package com.example.demo.domain.memo

import com.example.demo.infra.hawaii.Tables.MEMO
import com.example.demo.infra.hawaii.tables.records.MemoRecord
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
            .from(MEMO)
            .where(MEMO.DELETED_AT.isNull)
            .and(MEMO.USER_ID.eq(userId))
            .fetchOne().value1().toLong()
    }

    override fun save(userId: UUID, memoRecord: MemoRecord): MemoRecord {

        memoRecord.id = UUID.randomUUID()
        memoRecord.userId = userId

        val now = TimeUtil.getDateTimeNow()

        memoRecord.createdAt = now
        memoRecord.updatedAt = now

        return dsl.insertInto(MEMO)
            .set(memoRecord)
            .returning()
            .fetchOne()
    }

    override fun findAll(userId: UUID): List<MemoRecord> {

        return dsl.selectFrom(MEMO)
            .where(MEMO.DELETED_AT.isNull)
            .orderBy(MEMO.CREATED_AT.desc())
            .fetch()
    }

    override fun findAll(userId: UUID, size: Int, offset: Long): Pagination<MemoRecord> {

        val cnt = count(userId)

        val ret = dsl.selectFrom(MEMO)
            .where(MEMO.DELETED_AT.isNull)
            .and(MEMO.USER_ID.eq(userId))
            .orderBy(MEMO.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, cnt, ret)
    }

    override fun findById(userId: UUID, memoId: UUID): MemoRecord? {

        return dsl.selectFrom(MEMO)
            .where(MEMO.DELETED_AT.isNull)
            .and(MEMO.ID.eq(memoId))
            .and(MEMO.USER_ID.eq(userId))
            .orderBy(MEMO.CREATED_AT.desc())
            .fetchOne()
    }

    override fun updateById(userId: UUID, memoId: UUID, MemoRecord: MemoRecord): MemoRecord {

        MemoRecord.updatedAt = TimeUtil.getDateTimeNow()

        return dsl.update(MEMO)
            .set(MemoRecord)
            .where(MEMO.DELETED_AT.isNull)
            .and(MEMO.ID.eq(memoId))
            .and(MEMO.USER_ID.eq(userId))
            .returning().fetchOne()
    }

    override fun deleteById(userId: UUID, memoId: UUID): Int {

        return dsl.deleteFrom(MEMO)
            .where(MEMO.DELETED_AT.isNull)
            .and(MEMO.ID.eq(memoId))
            .and(MEMO.USER_ID.eq(userId))
            .execute()
    }

}
