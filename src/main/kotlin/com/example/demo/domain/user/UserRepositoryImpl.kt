package com.example.demo.domain.user

import com.example.demo.infra.hawaii.tables.Users
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Repository
class UserRepositoryImpl(
    private val dsl: DSLContext,
) : UserRepository {

    override fun findAll(userId: UUID): Mono<List<UsersRecord>> {

        val ret = dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetch()

        return ret.toMono()
    }

    override fun pagination(userId: UUID, size: Int, offset: Long): Mono<Pagination<UsersRecord>> {

        val count = dsl.selectCount()
            .from(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .fetchOne().value1().toLong()

        val ret = dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .orderBy(Users.USERS.CREATED_AT.desc())
            .limit(size).offset(offset)
            .fetch()

        return Pagination(size, offset, count, ret).toMono()
    }

    override fun findById(userId: UUID): Mono<UsersRecord> {

        val ret = dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.ID.eq(userId))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetchOne()

        return ret.toMono()
    }

    override fun findByEmail(email: String): Mono<UsersRecord> {

        val ret = dsl.selectFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.USERNAME.eq(email))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .fetchOne()

        return ret.toMono()
    }

    override fun deleteById(userId: UUID): Mono<Int> {

        val ret = dsl.deleteFrom(Users.USERS)
            .where(Users.USERS.DELETED_AT.isNull)
            .and(Users.USERS.ID.eq(userId))
            .orderBy(Users.USERS.CREATED_AT.desc())
            .execute()

        return ret.toMono()

    }

}
