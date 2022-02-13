package com.example.demo.security

import com.example.demo.app_service.token.TokenService
import com.example.demo.domain.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class JwtUserDetailService(
    private val tokenService: TokenService,
    private val userRepository: UserRepository,
) : ReactiveUserDetailsService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun findByUsername(username: String?): Mono<UserDetails> {
        userRepository.findByEmail(username!!)?.let {
            val jwtUserDetails = JwtUserDetails(
                id = it.id,

                username = it.username,
                password = it.passwordHash,
                nickname = it.nickname,
                authorities = it.authorities,

                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                deletedAt = it.deletedAt
            )

            return jwtUserDetails.toMono()
        }
        return Mono.empty()
    }

}
