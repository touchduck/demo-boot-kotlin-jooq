package com.example.demo.domain.user

import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import java.util.*

interface UserRepository {

    fun count(): Long

    fun findAll(): List<UsersRecord>

    fun findAll(size: Int, offset: Long): Pagination<UsersRecord>

    fun findById(userId: UUID): UsersRecord?

    fun findUsername(uername: String): UsersRecord?

    fun updateById(usersRecord: UsersRecord): UsersRecord?

    fun deleteById(userId: UUID): Int

}
