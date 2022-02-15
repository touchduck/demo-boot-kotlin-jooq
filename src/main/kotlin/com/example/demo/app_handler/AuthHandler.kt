package com.example.demo.app_handler

import com.example.demo.app_service.auth.AuthService
import com.example.demo.app_service.auth.SignUpParam
import com.example.demo.app_service.util.ErrorDto
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.created
import java.net.URI

@Component
class AuthHandler(
    private val authService: AuthService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    // 会員登録
    suspend fun signUp(request: ServerRequest): ServerResponse {

        return try {

            val signUpParam = request.awaitBody<SignUpParam>().validateObj()

            authService.isRegisterUser(signUpParam.username)?.let {
                return badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDto.dataDuplicated("auth"))
            }

            val user = authService.signUp(signUpParam)

            log.info("sign up user: {}", user.username)

            created(URI.create("/api/v1/users/${user.id}"))
                .buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

}
