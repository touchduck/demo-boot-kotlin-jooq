package com.example.demo.app_service.memo

import com.example.demo.domain.memo.MemoRepository
import com.example.demo.infra.hawaii.tables.records.MemoRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class MemoServiceImpl(
    private val modelMapper: ModelMapper,
    private val memoRepository: MemoRepository,
) : MemoService {

    private val log = LoggerFactory.getLogger(javaClass)

    // メモーの作成
    override fun create(userId: UUID, memoParam: MemoParam): MemoRecord? {

        val memo = modelMapper.map(memoParam, MemoRecord::class.java)
        val createdMemo = memoRepository.save(userId, memo)

        return createdMemo
    }

    // メモーの一覧
    override fun getList(userId: UUID, paginationParam: PaginationParam): Pagination<MemoRecord> {

        val memoList = memoRepository.findAll(userId, paginationParam.size, paginationParam.offset)

        return memoList
    }

    // メモーの詳細
    override fun getDetail(userId: UUID, memoId: UUID): MemoRecord? {

        memoRepository.findById(userId, memoId)?.let {
            return it
        }

        return null
    }

    // メモーの更新
    override fun update(userId: UUID, memoId: UUID, memoParam: MemoParam): MemoRecord? {

        val memo = modelMapper.map(memoParam, MemoRecord::class.java)

        val updatedMemo = memoRepository.updateById(userId, memoId, memo)

        return updatedMemo
    }

    // メモーの削除
    override fun delete(userId: UUID, memoId: UUID): Int {

        val ret = memoRepository.deleteById(userId, memoId)

        return ret
    }
}
