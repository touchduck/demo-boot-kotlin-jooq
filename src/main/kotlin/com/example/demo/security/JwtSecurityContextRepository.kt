package com.example.demo.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class JwtSecurityContextRepository(private val jwtAuthenticationManager: JwtAuthenticationManager) :
    ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        throw IllegalStateException("Not yet implemented")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {

        val token = Optional.ofNullable(exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith("Bearer ") }
            .map { it.substring(7) }

        if (token.isPresent)
            return jwtAuthenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(null, token.get()))
                .map(::SecurityContextImpl)

        return Mono.empty()
    }
}
