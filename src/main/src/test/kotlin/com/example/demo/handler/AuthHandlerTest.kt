package com.example.demo.handler

import com.example.demo.SpringWebFluxTest
import com.example.demo.domain.memo.Memo
import com.example.demo.domain.user.User
import com.example.demo.domain.user.UserParam
import com.example.demo.domain.userprofile.UserProfile
import com.example.demo.dto.SignInDTO
import com.example.demo.dto.TokenDTO
import com.example.demo.util.AuthUtil
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringWebFluxTest
class AuthHandlerTest(@Autowired override val client: WebTestClient) : AuthUtil(client) {

    @BeforeAll
    fun beforeAll() {
        transaction {
            Memo.table.deleteAll()
            User.table.deleteAll()
            UserProfile.table.deleteAll()
        }
    }

    @BeforeEach
    fun beforeEach() {
    }

    @AfterAll
    fun afterAll() {
    }

    @AfterEach
    fun afterEach() {
    }

    @Test
    @Order(1)
    fun signUpTest() {

        val param = UserParam(
            username = "kuma1@gmail.com",
            password = "starbucks",
            nickname = "くまさん1",
        )

        val result = apiPostExchange("$apiV1/auth/signup", param)
        result.expectStatus().isCreated
    }

    @Test
    @Order(2)
    fun tokenTest() {

        val param = SignInDTO(
            username = "kuma1@gmail.com",
            password = "starbucks"
        )

        val result = apiPostExchange("$apiV1/token", param)
        result.expectStatus().isOk

        val dto = result.returnResult<TokenDTO>().responseBody.blockFirst()
        token = dto!!.access_token
    }

}
