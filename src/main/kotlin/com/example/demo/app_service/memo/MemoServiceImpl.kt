package com.example.demo.app_service.memo

import com.example.demo.domain.memo.MemoRepository
import com.example.demo.infra.hawaii.tables.records.MemoRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Transactional
@Service
class MemoServiceImpl(
    private val modelMapper: ModelMapper,
    private val memoRepository: MemoRepository,
) : MemoService {

    private val log = LoggerFactory.getLogger(javaClass)

    // メモーの作成
    override suspend fun create(userId: UUID, memoParam: MemoParam): Mono<MemoRecord> {

        val memo = modelMapper.map(memoParam, MemoRecord::class.java)
        val createdMemo = memoRepository.save(userId, memo)

        return createdMemo.toMono()
    }

    // メモーの一覧
    override suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<MemoRecord>> {

        val memoList = memoRepository.findAll(userId, paginationParam.size, paginationParam.offset)

        return memoList.toMono()
    }

    // メモーの詳細
    override suspend fun getDetail(userId: UUID, memoId: UUID): Mono<MemoRecord> {

        memoRepository.findById(userId, memoId)?.let {
            return it.toMono()
        }

        return Mono.empty()
    }

    // メモーの更新
    override suspend fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): Mono<MemoRecord> {

        val memo = modelMapper.map(memoParam, MemoRecord::class.java)

        val updatedMemo = memoRepository.updateById(userId, memoId, memo)

        return updatedMemo.toMono()
    }

    // メモーの削除
    override suspend fun delete(userId: UUID, memoId: UUID): Mono<Int> {

        val ret = memoRepository.deleteById(userId, memoId)
        if (ret > 0) {
            return ret.toMono()
        }

        return Mono.empty()
    }
}
