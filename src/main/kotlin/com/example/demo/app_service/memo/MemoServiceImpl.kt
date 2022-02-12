package com.example.demo.app_service.memo

import com.example.demo.domain.memo.MemoParam
import com.example.demo.domain.memo.MemoRepository
import com.example.demo.infra.hawaii.tables.records.MemosRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Transactional
@Service
class MemoServiceImpl(
    private val memoRepository: MemoRepository,
) : MemoService {
    private val log = LoggerFactory.getLogger(javaClass)

    // メモーの作成
    override suspend fun create(userId: UUID, memoParam: MemoParam): Mono<MemosRecord> {

        memoRepository.create(userId, memoParam).awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }

    // メモーの一覧
    override suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<MemosRecord>> {

        memoRepository.pagination(userId, paginationParam.size, paginationParam.offset).awaitSingle()
            .let {
                return it.toMono()
            }
    }

    // メモーの詳細
    override suspend fun getDetail(userId: UUID, memoId: UUID): Mono<MemosRecord> {

        memoRepository.findById(userId, memoId).awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }

    // メモーの更新
    override suspend fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): Mono<MemosRecord> {

        memoRepository.update(userId, memoId, memoParam).toMono().awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }

    // メモーの削除
    override suspend fun delete(userId: UUID, memoId: UUID): Mono<Int> {

        memoRepository.deleteById(userId, memoId).awaitSingleOrNull()
            ?.let {
                return it.toMono()
            }

        return Mono.empty()
    }
}
