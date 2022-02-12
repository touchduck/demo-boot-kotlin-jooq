package com.example.demo.domain.userprofile

import com.example.demo.domain.user.User
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import reactor.core.publisher.Mono
import java.util.*

interface UserProfileRepository {
    fun create(ownerUser: User, userProfileParam: UserProfileParam): Mono<UserProfile>
    fun findAll(ownerUserId: UUID, paginationParam: PaginationParam): Mono<Pagination<UserProfile>>
    fun findById(ownerUserId: UUID): Mono<UserProfile>
    fun updateById(ownerUserId: UUID, userProfileParam: UserProfileParam): Mono<UserProfile>
    fun deleteById(ownerUserId: UUID): Mono<UserProfile>
}
