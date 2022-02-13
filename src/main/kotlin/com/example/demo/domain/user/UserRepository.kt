package com.example.demo.domain.user

import com.example.demo.infra.hawaii.tables.records.UserRecord
import com.example.demo.util.Pagination
import java.util.*

interface UserRepository {

    fun count(): Long

    fun save(userRecord: UserRecord): UserRecord

    fun findAll(): List<UserRecord>

    fun findAll(size: Int, offset: Long): Pagination<UserRecord>

    fun findById(userId: UUID): UserRecord?

    fun findUsername(username: String): UserRecord?

    fun update(userRecord: UserRecord): UserRecord?

    fun deleteById(userId: UUID): Int

}
