package com.example.demo.app_handler

import com.example.demo.SpringWebFluxTest
import com.example.demo.app_service.auth.SignUpParam
import com.example.demo.app_service.token.TokenParam
import com.example.demo.util.AuthUtil
import org.jooq.DSLContext
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.transaction.annotation.Transactional

@Transactional("transactionManager")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringWebFluxTest
class SignUpHandlerTest(@Autowired override val client: WebTestClient, @Autowired override val dsl: DSLContext) :
    AuthUtil(client, dsl) {

    @BeforeAll
    fun beforeAll() {
        clean()
    }

    @Test
    @Order(1)
    fun signUp_fail() {

        val param = SignUpParam(
            username = "kuma",
            password = "starbucks",
            nickname = "くまさんFail",
        )

        val result = apiPostExchange(apiSignUpUri, param)
        result.expectStatus().isBadRequest
    }

    @Test
    @Order(2)
    fun token_fail() {

        val param = TokenParam(
            username = "kuma@gmail.com",
            password = "starbucks"
        )

        val result = apiPostExchange(apiTokenUri, param)
        result.expectStatus().isNotFound
    }

}
