package com.example.demo.app_handler

import com.example.demo.app_service.memo.MemoParam
import com.example.demo.app_service.memo.MemoService
import com.example.demo.app_service.token.TokenService
import com.example.demo.app_service.util.ErrorDto
import com.example.demo.app_service.util.PaginationDto
import com.example.demo.domain.memo.toDto
import com.example.demo.util.PaginationParam
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI
import java.util.*

@Component
class MemoHandler(
    private val tokenService: TokenService,
    private val memoService: MemoService,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    // メモーの作成
    suspend fun create(request: ServerRequest): ServerResponse {

        return try {

            val userId = tokenService.getUserId(request)

            val memoParam = request.awaitBody<MemoParam>().validateObj()

            memoService.create(userId, memoParam).awaitSingleOrNull()
                ?.let {
                    return created(URI.create("/api/v1/memos/${it.id}")).contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(it.toDto())
                }

            badRequest().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(ErrorDto.dataFailedInsert("memo"))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    // メモーの一覧
    suspend fun getAll(request: ServerRequest): ServerResponse {

        return try {
            val userId = tokenService.getUserId(request)

            val pagination = memoService.getList(userId, PaginationParam(request)).awaitSingle()

            val memos = pagination.items.map { it.toDto() }

            ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(PaginationDto(pagination, memos))

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    // メモーの詳細
    suspend fun getById(request: ServerRequest): ServerResponse {

        return try {
            val userId = tokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            memoService.getDetail(userId, memoId).awaitSingleOrNull()
                ?.let {
                    return ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(it.toDto())
                }

            notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    // メモーの更新
    suspend fun update(request: ServerRequest): ServerResponse {

        return try {
            val userId = tokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            val memoParam = request.awaitBody<MemoParam>().validateObj()

            memoService.update(userId, memoId, memoParam).awaitSingleOrNull()
                ?.let {
                    return ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValueAndAwait(it.toDto())
                }

            notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

    // メモーの削除
    suspend fun delete(request: ServerRequest): ServerResponse {

        return try {
            val userId = tokenService.getUserId(request)
            val memoId = UUID.fromString(request.pathVariable("id"))

            memoService.delete(userId, memoId).awaitSingleOrNull()
                ?.let {
                    return ok().contentType(MediaType.APPLICATION_JSON).buildAndAwait()
                }

            notFound().buildAndAwait()

        } catch (e: Exception) {
            log.error(e.localizedMessage)
            badRequest().buildAndAwait()
        }
    }

}
