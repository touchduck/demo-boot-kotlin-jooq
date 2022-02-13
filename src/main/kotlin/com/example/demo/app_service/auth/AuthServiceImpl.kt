package com.example.demo.app_service.auth

import com.example.demo.app_service.user.UserService
import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.Users
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.TimeUtil
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Transactional
@Service
class AuthServiceImpl(
    private val dsl: DSLContext,
    private val userService: UserService,
    private val userRepository: UserRepository,
) : AuthService {

    private val log = LoggerFactory.getLogger(javaClass)

    override suspend fun signUp(signUpParam: SignUpParam): Mono<UsersRecord> {

        val now = TimeUtil.getDateTimeNow()
        val user = dsl.insertInto(Users.USERS)
            .set(Users.USERS.ID, UUID.randomUUID())
            .set(Users.USERS.USERNAME, signUpParam.username)
            .set(
                Users.USERS.PASSWORD_HASH,
                PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(signUpParam.password)
            )
            .set(Users.USERS.NICKNAME, signUpParam.nickname)
            .set(Users.USERS.AUTHORITIES, "ROLE_ADMIN")
            .set(Users.USERS.CREATED_AT, now)
            .set(Users.USERS.UPDATED_AT, now)
            .returning()
            .fetchOne()

        return user.toMono()
    }

    override suspend fun changePassword(userId: UUID, param: ChangePasswordParam): Mono<UsersRecord> {

        val user = userService.getDetail(userId).awaitSingleOrNull() ?: return Mono.empty()

        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(param.password)
        user.updatedAt = TimeUtil.getDateTimeNow()

        return user.toMono()
    }

    override suspend fun comparePassword(checkPassword: String, password_hash: String): Boolean {

        if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(checkPassword, password_hash)) {
            return true
        }
        return false

    }

    override suspend fun isRegisterUser(username: String): Mono<UsersRecord> {
        userRepository.findByEmail(username)?.let{
            return it.toMono()
        }

        return Mono.empty()
    }

    override suspend fun update(userId: UUID, param: UserSettingParam): Mono<UsersRecord> {

        userRepository.findById(userId)?.let{

            it.firstName = param.firstname
            it.lastName = param.lastname

            userRepository.updateById(it)?.let { itSub ->
                return itSub.toMono()
            }
        }

        return Mono.empty()
    }

}
