package com.example.demo.app_service.token

import com.example.demo.infra.hawaii.tables.records.UsersRecord
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.springframework.web.reactive.function.server.ServerRequest
import java.util.*

interface TokenService {

    fun validateToken(token: String): Optional<Jws<Claims>>

    fun generateToken(user: UsersRecord): String

    fun getToken(serverRequest: ServerRequest): Optional<String>?

    fun getUserId(serverRequest: ServerRequest): UUID
}
