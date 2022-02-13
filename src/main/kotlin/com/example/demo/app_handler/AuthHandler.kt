package com.example.demo.app_handler

import com.example.demo.app_service.auth.AuthService
import com.example.demo.app_service.auth.SignUpParam
import com.example.demo.app_service.auth.UserSettingParam
import com.example.demo.app_service.auth.toAuthDto
import com.example.demo.app_service.token.TokenService
import com.example.demo.app_service.util.ErrorDto
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class AuthHandler(
    private val tokenService: TokenService,
    private val authService: AuthService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    suspend fun create(request: ServerRequest): ServerResponse {

        return try {

            val signUpParam = request.awaitBody<SignUpParam>().validateObj()

            authService.isRegisterUser(signUpParam.username)?.let {
                return badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDto.dataDuplicated("auth"))
            }

            val user = authService.signUp(signUpParam)

            log.info("sign up user: {}", user.username)

            created(URI.create("/api/v1/users/${user.id}"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(user.toAuthDto())

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {

        return try {

            val userId = tokenService.getUserId(request)

            val userParam = request.awaitBody<UserSettingParam>().validateObj()

            authService.update(userId, userParam)

            notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

}
