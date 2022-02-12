package com.example.demo.security

import com.example.demo.app_service.token.TokenService
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager(private val tokenService: TokenService) : ReactiveAuthenticationManager {

    override fun authenticate(autenticacao: Authentication): Mono<Authentication> {
        val token = autenticacao.credentials.toString()
        return tokenService.validateToken(token)
            .map { Mono.just(it) }
            .orElse(Mono.empty())
            .map {
                UsernamePasswordAuthenticationToken(
                    it.body.subject, null,
                    mutableListOf(SimpleGrantedAuthority(it.body["roles"].toString()))
                )
            }
    }
}
