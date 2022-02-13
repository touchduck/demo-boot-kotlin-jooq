package com.example.demo.app_service.token

import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.records.UserRecord
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.Instant
import java.time.Period
import java.util.*

@Transactional
@Service
class TokenServiceImpl(
    private val userRepository: UserRepository,
) : TokenService {

    private val key =
        "Y3rlBBUPkX-UYjyy7bwPjuhkkPB0i8UGVFEoWjgten-ng5ltW0nWibn7S7njKbdLMlLifBDtWGTfAnG8-K6hw6LQjht6x0CrIS0M53yqOmEul56QY-YmW9-N0gnN9Om8d1G2Z-6hqwCIe9oL72f6moSEmlQmNR6RrBtoSeRd80_OUKNeb6j3fMXfFWmU30r1wk3RdyLcnqrZnardtWrwx"

    override fun validateToken(token: String) = Optional.ofNullable(
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

    override fun generateToken(user: UserRecord): String {

        val claims = Jwts.claims()

        claims["user_id"] = user.id

        claims["roles"] = user.authorities
//        claims["roles"] = user.getAuthorities().joinToString { it.authority }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer("TOUCH-DUCK")
            .setSubject(user.username)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(Period.ofDays(30))))
            .signWith(Keys.hmacShaKeyFor(this.key.toByteArray()))
            .compact()
    }

    override fun getToken(serverRequest: ServerRequest): Optional<String>? {
        return Optional.ofNullable(serverRequest.exchange().request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { it.startsWith("Bearer ") }
            .map { it.substring(7) }
    }

    override fun getUserId(serverRequest: ServerRequest): UUID {
        val token = getToken(serverRequest)?.get()
        val claims = validateToken(token!!).get()
        val userId = claims.body["user_id"].toString()
        return UUID.fromString(userId)
    }

}
