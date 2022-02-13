package com.example.demo.domain.user

import com.example.demo.infra.hawaii.Tables.USER
import com.example.demo.infra.hawaii.tables.records.UserRecord
import com.example.demo.util.Pagination
import com.example.demo.util.TimeUtil
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class UserRepositoryImpl(
    private val dsl: DSLContext,
) : UserRepository {

    override fun count(): Long {

        return dsl.selectCount()
            .from(USER)
            .where(USER.DELETED_AT.isNull)
            .fetchOne().value1().toLong()
    }

    override fun save(userRecord: UserRecord): UserRecord {

        userRecord.id = UUID.randomUUID()

        val now = TimeUtil.getDateTimeNow()

        userRecord.createdAt = now
        userRecord.updatedAt = now

        return dsl.insertInto(USER)
            .set(userRecord)
            .returning()
            .fetchOne()
    }

    override fun findAll(): List<UserRecord> {

        return dsl.selectFrom(USER)
            .where(USER.DELETED_AT.isNull)
            .orderBy(USER.CREATED_AT.desc())
            .fetch()
    }

    override fun findAll(size: Int, offset: Long): Pagination<UserRecord> {

        val cnt = count()

        val ret = dsl.selectFrom(USER)
            .where(USER.DELETED_AT.isNull)
            .orderBy(USER.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, cnt, ret)
    }

    override fun findById(userId: UUID): UserRecord? {

        return dsl.selectFrom(USER)
            .where(USER.DELETED_AT.isNull)
            .and(USER.ID.eq(userId))
            .orderBy(USER.CREATED_AT.desc())
            .fetchOne()
    }

    override fun findUsername(username: String): UserRecord? {

        return dsl.selectFrom(USER)
            .where(USER.DELETED_AT.isNull)
            .and(USER.USERNAME.eq(username))
            .orderBy(USER.CREATED_AT.desc())
            .fetchOne()
    }

    override fun update(userRecord: UserRecord): UserRecord? {

        val now = TimeUtil.getDateTimeNow()

        userRecord.updatedAt = now

        return dsl.update(USER)
            .set(userRecord)
            .where(USER.DELETED_AT.isNull)
            .and(USER.ID.eq(userRecord.id))
            .returning().fetchOne()
    }

    override fun deleteById(userId: UUID): Int {

        return dsl.deleteFrom(USER)
            .where(USER.DELETED_AT.isNull)
            .and(USER.ID.eq(userId))
            .orderBy(USER.CREATED_AT.desc())
            .execute()

    }

}
