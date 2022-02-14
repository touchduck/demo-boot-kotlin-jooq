package com.example.demo.util

import com.example.demo.app_service.auth.SignUpParam
import com.example.demo.app_service.token.TokenDto
import com.example.demo.app_service.token.TokenParam
import com.example.demo.infra.hawaii.Tables.MEMO
import com.example.demo.infra.hawaii.Tables.USER
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import java.time.Duration

open class AuthUtil(@Autowired open val client: WebTestClient, @Autowired open val dsl: DSLContext) {

    var apiSignUpUri = "/api/v1/signup"
    var apiTokenUri = "/api/v1/token"

    var token: String = ""
        get() = "Bearer $field"

    fun clean() {

        dsl.delete(MEMO).execute()
        dsl.delete(USER).execute()

        signUp("yikyunbum@gmail.com", "starbucks", "くまさん")

        signUp("user1@gmail.com", "starbucks", "ユーザー１")
        signUp("user2@gmail.com", "starbucks", "ユーザー２")
        signUp("user3@gmail.com", "starbucks", "ユーザー３")

    }

    fun signUp(username: String, password: String, nickname: String) {

        val param = SignUpParam(
            username = username,
            password = password,
            nickname = nickname,
        )

        val result = client.post().uri(apiSignUpUri)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(param)
            .exchange()

        result.expectStatus().isCreated
    }

    fun signIn(username: String, password: String) {

        val param = TokenParam(
            username = username,
            password = password
        )

        val result = client.post().uri(apiTokenUri)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(param)
            .exchange()

        result.expectStatus().isOk

        val dto = result.returnResult<TokenDto>().responseBody.blockFirst()
        token = dto!!.access_token
    }

    fun apiPostExchange(uri: String, param: Any): WebTestClient.ResponseSpec {
        return client.mutate().responseTimeout(Duration.ofMinutes(1)).build().post().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).bodyValue(param).exchange()
    }

    fun apiGetExchange(uri: String): WebTestClient.ResponseSpec {
        return client.mutate().responseTimeout(Duration.ofMinutes(1)).build().get().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).exchange()
    }

    fun apiPutExchange(uri: String, param: Any): WebTestClient.ResponseSpec {
        return client.mutate().responseTimeout(Duration.ofMinutes(1)).build().put().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).bodyValue(param).exchange()
    }

    fun apiDeleteExchange(uri: String): WebTestClient.ResponseSpec {
        return client.mutate().responseTimeout(Duration.ofMinutes(1)).build().delete().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).exchange()
    }

}
