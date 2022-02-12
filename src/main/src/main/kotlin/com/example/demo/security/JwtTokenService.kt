package com.example.demo.security

import com.example.demo.domain.user.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.Instant
import java.time.Period
import java.util.*


@Service
class JwtTokenService {

    private val key =
        "jefaokpta102030405060708090asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdjefao123"

    fun validateToken(token: String) = Optional.ofNullable(
        try {
            Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(this.key.toByteArray()))
                .build()
                .parseClaimsJws(token)
        } catch (exception: Exception) {
            println(":::: INVALID TOKEN! $token")
            null
        }
    )

    fun generateToken(user: User): String {

        val claims = Jwts.claims()

        claims["user_id"] = user.id.value
        claims["roles"] = user.getAuthorities().joinToString { it.authority }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer("TOUCH-DUCK")
            .setSubject(user.username)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(Period.ofDays(30))))
            .signWith(Keys.hmacShaKeyFor(this.key.toByteArray()))
            .compact()
    }

    fun getToken(serverRequest: ServerRequest): Optional<String>? {
        return Optional.ofNullable(serverRequest.exchange().request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith("Bearer ") }
            .map { it.substring(7) }
    }

    fun getUserId(serverRequest: ServerRequest): UUID {
        val token = getToken(serverRequest)?.get()
        val claims = validateToken(token!!).get()
        val userId = claims.body["user_id"].toString()
        return UUID.fromString(userId)
    }

}
