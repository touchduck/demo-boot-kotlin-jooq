package com.example.demo.domain.user

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import reactor.core.publisher.Mono
import java.util.*

interface UserRepository : ReactiveUserDetailsService {
    fun create(userParam: UserParam): Mono<User>
    fun findById(userId: UUID): Mono<User>
    fun findByEmail(username: String): Mono<User>
}
