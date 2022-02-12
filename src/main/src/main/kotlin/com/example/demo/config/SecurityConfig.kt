package com.example.demo.config

import com.example.demo.domain.user.UserRepository
import com.example.demo.security.JwtAuthenticationManager
import com.example.demo.security.JwtSecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationManager: JwtAuthenticationManager,
    private val jwtSecurityContextRepository: JwtSecurityContextRepository,
) {

    private val apiV1 = "/api/v1"

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.cors().and()
            .authenticationManager(jwtAuthenticationManager)
            .securityContextRepository(jwtSecurityContextRepository)
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST, "$apiV1/token").permitAll()
            .pathMatchers(HttpMethod.POST, "$apiV1/auth/signup").permitAll()
            .pathMatchers("$apiV1/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and().csrf().disable()
            .build()
    }

    @Bean
    fun authenticationManager(userRepository: UserRepository): ReactiveAuthenticationManager {
        return UserDetailsRepositoryReactiveAuthenticationManager(userRepository)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
