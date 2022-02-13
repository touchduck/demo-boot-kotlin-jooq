package com.example.demo.app_service.user

import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.Users
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import com.example.demo.util.TimeUtil
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.jooq.DSLContext
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Transactional
@Service
class UserServiceImpl(
    private val dsl: DSLContext,
    private val modelMapper: ModelMapper,
    private val userRepository: UserRepository,
) : UserService {

    private val log = LoggerFactory.getLogger(javaClass)

    override suspend fun create(userId: UUID, userParam: UserParam): Mono<UsersRecord> {

        val newUer = dsl.insertInto(Users.USERS)
            .set(Users.USERS.USERNAME, userParam.username)
            .set(
                Users.USERS.PASSWORD_HASH,
                PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userParam.password)
            )
            .returning()
            .fetchOne()

        return newUer.toMono()
    }

    override suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<UsersRecord>> {

        userRepository.pagination(userId, paginationParam.size, paginationParam.offset).awaitSingle()
            .let {
                return it.toMono()
            }
    }

    override suspend fun getDetail(userId: UUID): Mono<UsersRecord> {

        userRepository.findById(userId).awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }

    override suspend fun update(userId: UUID, userParam: UserParam): Mono<UsersRecord> {

        val user = userRepository.findById(userId).awaitSingleOrNull() ?: return Mono.empty()

        user.nickname = userParam.nickname
        user.updatedAt = TimeUtil.getDateTimeNow()

        return user.toMono()
    }

    override suspend fun delete(userId: UUID): Mono<Int> {

        userRepository.deleteById(userId).awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }

}
