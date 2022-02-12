package com.example.demo.security

import com.example.demo.app_service.token.TokenService
import com.example.demo.domain.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class JwtUserDetailService(
    private val tokenService: TokenService,
    private val userRepository: UserRepository,
) : ReactiveUserDetailsService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun findByUsername(username: String?): Mono<UserDetails> {
        return userRepository.findByEmail(username!!).map(JwtUserDetails::toMapping)
    }

}
