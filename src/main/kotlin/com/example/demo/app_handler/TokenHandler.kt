package com.example.demo.app_handler

import com.example.demo.app_service.auth.AuthService
import com.example.demo.app_service.token.TokenDto
import com.example.demo.app_service.token.TokenParam
import com.example.demo.app_service.token.TokenService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*

@Component
class TokenHandler(
    private val tokenService: TokenService,
    private val authService: AuthService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    suspend fun create(request: ServerRequest): ServerResponse {

        return try {

            val signInDTO = request.awaitBody<TokenParam>().validateObj()

            authService.isRegisterUser(signInDTO.username)?.let {

                if (!authService.comparePassword(signInDTO.password, it.passwordHash)) {
                    return badRequest().buildAndAwait()
                }

                val token = tokenService.generateToken(it)

                return ok().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(TokenDto(token, ""))
            }

            return notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }

    }

}
