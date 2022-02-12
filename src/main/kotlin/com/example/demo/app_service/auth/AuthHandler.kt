package com.example.demo.app_service.auth

import com.example.demo.app_service.auth.param.SignUpParam
import com.example.demo.app_service.auth.param.UserSettingParam
import com.example.demo.app_service.token.TokenService
import com.example.demo.app_service.util.ErrorDto
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.created
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

            authService.isRegistedUser(signUpParam.username).awaitSingleOrNull()
                ?.let {
                    return badRequest().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(ErrorDto.dataDuplicated("auth"))
                }

            val registeredUser = authService.signUp(signUpParam).awaitSingle()

            log.info("registered new user: {}", registeredUser.username)

            created(URI.create("/api/v1/users/${registeredUser.id}")).contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(AuthDto.toMapping(registeredUser))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {

        return try {
            val userId = tokenService.getUserId(request)

            val userParam = request.awaitBody<UserSettingParam>().validateObj()

            val updatedUser = authService.update(userId, userParam).awaitSingleOrNull()

            ServerResponse.notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

}
