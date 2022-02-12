package com.example.demo.domain.userprofile

import com.example.demo.domain.user.User
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import com.example.demo.util.TimeUtil
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Transactional
@Repository
class UserProfileRepositoryImpl : UserProfileRepository {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(ownerUser: User, userProfileParam: UserProfileParam): Mono<UserProfile> {

        return UserProfile.find {
            UserProfiles.userId.eq(ownerUser.id)
                .and(UserProfiles.deletedAt.isNull())

        }.singleOrNull()?.let {
            it.tel = userProfileParam.tel
            it.updatedAt = TimeUtil.getDateTimeNow()
            return Mono.just(it)

        } ?: return UserProfile.new {
            user = ownerUser
            tel = userProfileParam.tel

        }.toMono()

    }

    override fun findAll(ownerUserId: UUID, paginationParam: PaginationParam): Mono<Pagination<UserProfile>> {

        val query = UserProfiles.select { (UserProfiles.userId eq ownerUserId) and (UserProfiles.deletedAt.isNull()) }
        val total = query.count()

        val userProfiles = query.orderBy(UserProfiles.updatedAt to SortOrder.DESC)
            .limit(paginationParam.size, paginationParam.offset)
            .toList().map { UserProfile.wrapRow(it) }

        return Pagination(paginationParam.page, paginationParam.size, total, userProfiles).toMono()
    }

    override fun findById(ownerUserId: UUID): Mono<UserProfile> {
        return UserProfile.find {
            UserProfiles.userId.eq(ownerUserId)
                .and(UserProfiles.deletedAt.isNull())

        }.singleOrNull().toMono()
    }

    override fun updateById(ownerUserId: UUID, userProfileParam: UserProfileParam): Mono<UserProfile> {

        return UserProfile.find {
            UserProfiles.userId.eq(ownerUserId)
                .and(UserProfiles.deletedAt.isNull())

        }.singleOrNull()?.let {
            it.tel = userProfileParam.tel
            it.updatedAt = TimeUtil.getDateTimeNow()
            return Mono.just(it)

        }.toMono()
    }

    override fun deleteById(ownerUserId: UUID): Mono<UserProfile> {

        return UserProfile.find {
            UserProfiles.userId.eq(ownerUserId)
                .and(UserProfiles.deletedAt.isNull())

        }.singleOrNull()?.let {
            it.deletedAt = TimeUtil.getDateTimeNow()
            it.toMono()

        } ?: Mono.empty()
    }

}
