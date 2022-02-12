package com.example.demo.domain.user

import com.example.demo.security.JwtUserDetails
import org.jetbrains.exposed.sql.and
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*


@Transactional
@Repository
class UserRepositoryImpl : UserRepository {

    private val log = LoggerFactory.getLogger(javaClass)

    // ReactiveUserDetailsService
    override fun findByUsername(username: String?): Mono<UserDetails> {

        return User.find {
            Users.username.eq(username!!)
                .and(Users.deletedAt.isNull())
        }.map(JwtUserDetails::toMapping).singleOrNull().toMono()
    }

    override fun create(userParam: UserParam): Mono<User> {

        if (User.find {
                Users.username.eq(userParam.username)
                    .and(Users.deletedAt.isNull())

            }.count() > 0) {
            return Mono.empty()
        }

        return User.new {
            username = userParam.username
            passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userParam.password)
            nickname = userParam.nickname
            authorities = "ROLE_ADMIN"

        }.toMono()
    }

    override fun findById(userId: UUID): Mono<User> {

        return User.find {
            Users.id.eq(userId)
                .and(Users.deletedAt.isNull())

        }.singleOrNull().toMono()
    }

    override fun findByEmail(username: String): Mono<User> {

        return User.find {
            Users.username.eq(username)
                .and(Users.deletedAt.isNull())

        }.singleOrNull().toMono()
    }

}
