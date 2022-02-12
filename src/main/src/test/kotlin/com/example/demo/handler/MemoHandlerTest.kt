package com.example.demo.handler

import com.example.demo.SpringWebFluxTest
import com.example.demo.domain.memo.Memo
import com.example.demo.domain.memo.MemoParam
import com.example.demo.domain.user.User
import com.example.demo.dto.MemoDTO
import com.example.demo.util.AuthUtil
import com.example.demo.util.PagingDTO
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import java.util.*
import kotlin.collections.ArrayList

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringWebFluxTest
class MemoHandlerTes(@Autowired override val client: WebTestClient) : AuthUtil(client) {

    private var memoUri = "$apiV1/memos"

    private val memoIds = ArrayList<String>()

    @BeforeAll
    fun beforeAll() {

        transaction {
            Memo.table.deleteAll()
            User.table.deleteAll()
        }

        signUp()
        signIn()
    }

    // メモーの一作成
    @Test
    @Order(1)
    fun createTest() {

        for (cnt in 1..10) {

            val param = MemoParam(
                title = "テストメモー $cnt",
                body = "初めましてこれはテストメモーです $cnt",
            )

            val result = apiPostExchange(memoUri, param)

            result.expectStatus().isCreated

            val dto = result.returnResult<MemoDTO>().responseBody.blockFirst()
            memoIds.add(dto!!.id)
        }

    }

    // メモーの一覧
    @Test
    @Order(2)
    fun listTest() {

        val result = apiGetExchange(memoUri)

        result.expectStatus().isOk

        val dto = result.returnResult<PagingDTO<MemoDTO>>().responseBody.blockFirst()
        println(dto!!)
    }

    // メモーの詳細
    @Test
    @Order(3)
    fun detailTest() {

        val memoId = memoIds.last()

        val result = apiGetExchange("$memoUri/$memoId")

        result.expectStatus().isOk

        val dto = result.returnResult<MemoDTO>().responseBody.blockFirst()
        println(dto!!)
    }

    // メモーの詳細
    @Test
    @Order(4)
    fun detailNotExistTest() {

        val memoId = UUID.randomUUID()
        val result = apiGetExchange("$memoUri/$memoId")
        result.expectStatus().isNotFound

    }

    // メモーの更新
    @Test
    @Order(5)
    fun updateTest() {

        val memoId = memoIds.last()

        val param = MemoParam(
            title = "テスト更新メモー ",
            body = "初めましてこれは更新テストメモーです",
        )

        apiPutExchange("$memoUri/$memoId", param).expectStatus().isOk

        val dto = apiGetExchange("$memoUri/$memoId").returnResult<MemoDTO>().responseBody.blockFirst()

        assertEquals(param.title, dto?.title)
        assertEquals(param.body, dto?.body)
    }

    // メモーの削除
    @Test
    @Order(6)
    fun deleteTest() {

        for (memoId in memoIds.reversed()) {

            if (memoIds.size < 4) break

            apiDeleteExchange("$memoUri/$memoId").expectStatus().isOk

            apiGetExchange("$memoUri/$memoId").expectStatus().isNotFound

            memoIds.removeLast()
        }
    }

    // メモーの一覧 (件)
    @Test
    @Order(7)
    fun listCountTest() {

        val result = apiGetExchange(memoUri).expectStatus().isOk
        val dto = result.returnResult<PagingDTO<MemoDTO>>().responseBody.blockFirst()

        assertEquals(dto?.total, 3)
    }

}
