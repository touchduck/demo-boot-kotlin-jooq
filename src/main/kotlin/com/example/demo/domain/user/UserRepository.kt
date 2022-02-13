package com.example.demo.domain.user

import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import reactor.core.publisher.Mono
import java.util.*

interface UserRepository {

    fun findAll(userId: UUID): Mono<List<UsersRecord>>

    fun pagination(userId: UUID, size: Int, offset: Long): Mono<Pagination<UsersRecord>>

    fun findById(userId: UUID): Mono<UsersRecord>

    fun findByEmail(email: String): Mono<UsersRecord>

    fun deleteById(userId: UUID): Mono<Int>

}
