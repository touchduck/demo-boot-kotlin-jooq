package com.example.demo.app_handler

import com.example.demo.SpringWebFluxTest
import com.example.demo.app_service.memo.MemoParam
import com.example.demo.domain.memo.MemoDto
import com.example.demo.util.AuthUtil
import com.example.demo.util.PagingDto
import org.jooq.DSLContext
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional("transactionManager")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringWebFluxTest
class MemoHandlerTes(@Autowired override val client: WebTestClient, @Autowired override val dsl: DSLContext) :
    AuthUtil(client, dsl) {

    private var apiMemoUri = "/api/v1/memos"

    private val memoIds = ArrayList<String>()

    @BeforeAll
    fun beforeAll() {
        clean()
        signIn("yikyunbum@gmail.com", "starbucks")
    }

    // メモーの一作成
    @Test
    @Order(1)
    fun create() {

        for (cnt in 1..10) {

            val param = MemoParam(
                title = "テストメモー $cnt",
                body = "初めましてこれはテストメモーです $cnt",
            )

            val result = apiPostExchange(apiMemoUri, param)

            result.expectStatus().isCreated

            val dto = result.returnResult<MemoDto>().responseBody.blockFirst()
            memoIds.add(dto!!.id)
        }

    }

    // メモーの一覧
    @Test
    @Order(2)
    fun list() {

        val result = apiGetExchange("$apiMemoUri?page=0&size=3")

        result.expectStatus().isOk

        val dto = result.returnResult<PagingDto<MemoDto>>().responseBody.blockFirst()
        assertEquals(10, dto!!.total)
        assertEquals(3, dto.items.size)
    }

    // メモーの詳細
    @Test
    @Order(3)
    fun detail() {

        val memoId = memoIds.last()

        val result = apiGetExchange("$apiMemoUri/$memoId")

        result.expectStatus().isOk

        val dto = result.returnResult<MemoDto>().responseBody.blockFirst()
        assertEquals("テストメモー 10", dto!!.title)
    }

    // メモーの詳細
    @Test
    @Order(4)
    fun detailNotExist() {

        val memoId = UUID.randomUUID()
        val result = apiGetExchange("$apiMemoUri/$memoId")
        result.expectStatus().isNotFound

    }

    // メモーの更新
    @Test
    @Order(5)
    fun update() {

        val memoId = memoIds.last()

        val param = MemoParam(
            title = "テスト更新メモー",
            body = "初めましてこれは更新テストメモーです",
        )

        apiPutExchange("$apiMemoUri/$memoId", param).expectStatus().isOk

        val dto = apiGetExchange("$apiMemoUri/$memoId").returnResult<MemoDto>().responseBody.blockFirst()

        assertEquals(param.title, dto?.title)
        assertEquals(param.body, dto?.body)
    }

    // メモーの削除
    @Test
    @Order(6)
    fun delete() {

        for (memoId in memoIds.reversed()) {

            if (memoIds.size < 4) break

            apiDeleteExchange("$apiMemoUri/$memoId").expectStatus().isOk

            apiGetExchange("$apiMemoUri/$memoId").expectStatus().isNotFound

            memoIds.removeLast()
        }
    }

    // メモーの一覧 (件)
    @Test
    @Order(7)
    fun listCount() {

        val result = apiGetExchange(apiMemoUri).expectStatus().isOk
        val dto = result.returnResult<PagingDto<MemoDto>>().responseBody.blockFirst()

        assertEquals(dto?.total, 3)
    }

}
