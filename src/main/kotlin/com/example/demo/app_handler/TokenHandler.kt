package com.example.demo.app_handler

import com.example.demo.app_service.auth.AuthService
import com.example.demo.app_service.token.TokenService
import com.example.demo.domain.token.TokenDto
import com.example.demo.domain.token.TokenParam
import kotlinx.coroutines.reactor.awaitSingleOrNull
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

            val user = authService.isRegistedUser(signInDTO.username).awaitSingleOrNull()
                ?: return notFound().buildAndAwait()

            if (!authService.comparePassword(signInDTO.password, user.passwordHash)) {
                return badRequest().buildAndAwait()
            }

            val token = tokenService.generateToken(user)

            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(TokenDto(token, ""))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }

    }

}
