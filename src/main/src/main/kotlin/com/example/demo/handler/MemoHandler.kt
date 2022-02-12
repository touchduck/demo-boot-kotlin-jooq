package com.example.demo.handler

import com.example.demo.domain.memo.MemoParam
import com.example.demo.domain.memo.MemoRepository
import com.example.demo.domain.user.UserRepository
import com.example.demo.dto.ErrorDTO
import com.example.demo.dto.MemoDTO
import com.example.demo.dto.PaginationDTO
import com.example.demo.security.JwtTokenService
import com.example.demo.util.PaginationParam
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI
import java.util.*

@Component
class MemoHandler(
    private val jwtTokenService: JwtTokenService,
    private val userRepository: UserRepository,
    private val memoRepository: MemoRepository,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    private val handlerName = javaClass.simpleName

    // メモーの作成
    suspend fun create(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val param = request.awaitBody<MemoParam>()

            userRepository.findById(userId).awaitSingleOrNull()
                ?.let { user ->
                    return memoRepository.create(user, param).awaitSingleOrNull()
                        ?.let {
                            created(URI.create("/memos/${it.id.value}")).contentType(MediaType.APPLICATION_JSON)
                                .bodyValueAndAwait(MemoDTO.toMapping(it))
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

    // メモーの一覧
    suspend fun findAll(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val pagination = memoRepository.findAll(userId, PaginationParam(request)).awaitSingle()

            val memos = pagination.items.map(MemoDTO::toMapping)

            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(PaginationDTO(pagination, memos))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    // メモーの詳細
    suspend fun findById(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            memoRepository.findById(userId, memoId).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(MemoDTO.toMapping(it))
                }
                ?: notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    // メモーの更新
    suspend fun update(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            val param = request.awaitBody<MemoParam>()

            memoRepository.updateById(userId, memoId, param).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON).buildAndAwait()
                }
                ?: notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }

    // メモーの削除
    suspend fun delete(request: ServerRequest): ServerResponse {

        return try {

            val userId = jwtTokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            memoRepository.deleteById(userId, memoId).awaitSingleOrNull()
                ?.let {
                    ok().contentType(MediaType.APPLICATION_JSON).buildAndAwait()
                }
                ?: notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDTO.dataException(handlerName, e.localizedMessage))
        }
    }
}
