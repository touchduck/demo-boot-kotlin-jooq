package com.example.demo.handler

import com.example.demo.domain.user.UserRepository
import com.example.demo.domain.userprofile.UserProfileParam
import com.example.demo.domain.userprofile.UserProfileRepository
import com.example.demo.dto.ErrorDTO
import com.example.demo.dto.UserProfileDTO
import com.example.demo.security.JwtTokenService
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class UserProfileHandler(
    private val jwtTokenService: JwtTokenService,
    private val userRepository: UserRepository,
    private val userProfileRepository: UserProfileRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    private val handlerName = javaClass.simpleName

    suspend fun create(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val param = request.awaitBody<UserProfileParam>()

            userRepository.findById(userId).awaitSingleOrNull()
                ?.let { user ->
                    return userProfileRepository.create(user, param).awaitSingleOrNull()
                        ?.let {
                            ok().contentType(MediaType.APPLICATION_JSON)
                                .bodyValueAndAwait(UserProfileDTO.toMapping(it))
                        }
                        ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                            .bodyValueAndAwait(ErrorDTO.dataFailedInsert(handlerName))
                }
                ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO.dataFailedInsert(handlerName))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    suspend fun findById(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)

            userProfileRepository.findById(userId).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(UserProfileDTO.toMapping(it))
                }
                ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO.dataNotFound(handlerName))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val param = request.awaitBody<UserProfileParam>()

            userProfileRepository.updateById(userId, param).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(UserProfileDTO.toMapping(it))
                }
                ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO.dataFailedInsert(handlerName))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    suspend fun delete(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)

            userProfileRepository.deleteById(userId).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON).buildAndAwait()
                }
                ?: badRequest().contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(ErrorDTO.dataNotFound(handlerName))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

}
