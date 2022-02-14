package com.example.demo.app_handler

import com.example.demo.app_service.auth.AuthService
import com.example.demo.app_service.auth.UserParam
import com.example.demo.app_service.token.TokenService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class UserHandler(
    private val tokenService: TokenService,
    private val authService: AuthService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    // 会員情報の修正
    suspend fun update(request: ServerRequest): ServerResponse {

        return try {

            val userId = tokenService.getUserId(request)

            val userParam = request.awaitBody<UserParam>().validateObj()

            authService.update(userId, userParam)

            notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

}
