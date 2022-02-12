package com.example.demo.handler

import com.example.demo.domain.user.UserParam
import com.example.demo.domain.user.UserRepository
import com.example.demo.dto.ErrorDTO
import com.example.demo.dto.SignInDTO
import com.example.demo.dto.TokenDTO
import com.example.demo.dto.UserDTO
import com.example.demo.security.JwtTokenService
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

@Component
class AuthHandler(
    private val jwtTokenService: JwtTokenService,
    private val userRepository: UserRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    private val handlerName = javaClass.simpleName

    suspend fun signUp(request: ServerRequest): ServerResponse {

        return try {

            val param = request.awaitBody<UserParam>()

            userRepository.findByEmail(param.username).awaitSingleOrNull()
                ?.let {
                    return badRequest().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(ErrorDTO.dataDuplicated(handlerName))
                }
                ?: userRepository.create(param).awaitSingleOrNull()
                    ?.let {
                        created(URI.create("/users/${it.id.value}")).contentType(MediaType.APPLICATION_JSON)
                            .bodyValueAndAwait(UserDTO.toMapping(it))
                    }
                ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO(handlerName, "failed regist user"))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    suspend fun createToken(request: ServerRequest): ServerResponse {

        return try {

            val signInDTO = request.awaitBody<SignInDTO>()

            val user = userRepository.findByEmail(signInDTO.username).awaitSingleOrNull()

            if (user != null) {

                if (PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .matches(signInDTO.password, user.passwordHash)
                ) {
                    val token = jwtTokenService.generateToken(user)

                    ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(TokenDTO(token, ""))

                } else {
                    badRequest().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(ErrorDTO.dataNotEqualPassword(handlerName))
                }

            } else {
                badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO.dataNotFound(handlerName))
            }

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }
}
