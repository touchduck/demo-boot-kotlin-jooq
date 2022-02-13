package com.example.demo.domain.user

import com.example.demo.infra.hawaii.tables.Users
import com.example.demo.infra.hawaii.tables.records.UsersRecord
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
            .from(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .fetchOne().value1().toLong()
    }

    override fun save(usersRecord: UsersRecord): UsersRecord {

        usersRecord.id = UUID.randomUUID()

        val now = TimeUtil.getDateTimeNow()

        usersRecord.createdAt = now
        usersRecord.updatedAt = now

        return dsl.insertInto(Users.USERS)
            .set(usersRecord)
            .returning()
            .fetchOne()
    }

    override fun findAll(): List<UsersRecord> {

        return dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetch()
    }

    override fun findAll(size: Int, offset: Long): Pagination<UsersRecord> {

        val cnt = count()

        val ret = dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .orderBy(Users.USERS.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, cnt, ret)
    }

    override fun findById(userId: UUID): UsersRecord? {

        return dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.ID.eq(userId))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetchOne()
    }

    override fun findUsername(username: String): UsersRecord? {

        return dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.USERNAME.eq(username))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetchOne()
    }

    override fun update(usersRecord: UsersRecord): UsersRecord? {

        val now = TimeUtil.getDateTimeNow()

        usersRecord.updatedAt = now

        return dsl.update(Users.USERS)
            .set(usersRecord)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.ID.eq(usersRecord.id))
            .returning().fetchOne()
    }

    override fun deleteById(userId: UUID): Int {

        return dsl.deleteFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.ID.eq(userId))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .execute()

    }

}
